package com.brunosoares.ifood.mp.service;

import com.brunosoares.ifood.mp.dto.PedidoRealizadoDTO;
import com.brunosoares.ifood.mp.dto.PratoCarrinhoDTO;
import com.brunosoares.ifood.mp.dto.PratoDTO;
import com.brunosoares.ifood.mp.dto.PratoPedidoDTO;
import com.brunosoares.ifood.mp.dto.RestauranteDTO;
import com.brunosoares.ifood.mp.entity.PratoCarrinho;
import com.brunosoares.ifood.mp.repository.PratoCarrinhoRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class CarrinhoService {

    private static final String CLIENTE_FAKE = "a";

    @Inject
    PratoCarrinhoRepository pratoCarrinhoRepository;

    @Inject
    PratoService pratoService;

    @Inject
    @Channel("pedidos")
    Emitter<PedidoRealizadoDTO> emitter;

    public Multi<PratoCarrinhoDTO> buscarCarrinhoPeloCliente() {
        final Uni<RowSet<Row>> query = this.pratoCarrinhoRepository.buscarCarrinhoPeloCliente(CLIENTE_FAKE);

        return this.uniToMulti(query);
    }

    public PratoCarrinhoDTO adicionarPrato(final Long idPrato) {
        final PratoDTO prato = this.pratoService.buscarPeloID(idPrato).collect()
                .first()
                .await()
                .indefinitely();
        if (prato == null) {
            throw new IllegalArgumentException("Prato inexistente");
        }

        final RowSet<Row> query = this.pratoCarrinhoRepository.inserir(PratoCarrinho.builder()
                .prato(idPrato)
                .cliente(CLIENTE_FAKE)
                .build());

        return PratoCarrinhoDTO.from(query.iterator().next());
    }

    public void finalizarPedido() {
        final PedidoRealizadoDTO pedidoRealizado = new PedidoRealizadoDTO();
        pedidoRealizado.setCliente(CLIENTE_FAKE);

        final List<PratoCarrinhoDTO> pratoCarrinho = this.buscarCarrinhoPeloCliente().collect()
                .asList()
                .await()
                .indefinitely();
        pedidoRealizado.setPratos(pratoCarrinho.stream().map(this::toPratoPedidoDTO).collect(Collectors.toList()));

        final RestauranteDTO restaurante = new RestauranteDTO();
        restaurante.setNome("Nome restaurante");
        pedidoRealizado.setRestaurante(restaurante);

        this.emitter.send(pedidoRealizado);

        this.pratoCarrinhoRepository.deletarPeloCliente(CLIENTE_FAKE);
    }

    private PratoPedidoDTO toPratoPedidoDTO(final PratoCarrinhoDTO pratoCarrinhoDTO) {
        final PratoDTO prato = this.pratoService.buscarPeloID(pratoCarrinhoDTO.getPrato()).collect()
                .first()
                .await()
                .indefinitely();
        return new PratoPedidoDTO(prato.getNome(), prato.getDescricao(), prato.getPreco());
    }

    private Multi<PratoCarrinhoDTO> uniToMulti(final Uni<RowSet<Row>> query) {
        return query.onItem()
                .transformToMulti(RowSet::toMulti)
                .onItem().transform(PratoCarrinhoDTO::from);
    }

}
