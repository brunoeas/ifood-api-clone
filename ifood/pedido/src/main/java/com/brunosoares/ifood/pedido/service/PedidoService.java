package com.brunosoares.ifood.pedido.service;

import com.brunosoares.ifood.pedido.dto.PedidoRealizadoDTO;
import com.brunosoares.ifood.pedido.dto.PratoPedidoDTO;
import com.brunosoares.ifood.pedido.entity.Pedido;
import com.brunosoares.ifood.pedido.entity.Prato;
import com.brunosoares.ifood.pedido.entity.Restaurante;
import com.brunosoares.ifood.pedido.repository.PedidoRepository;
import org.bson.types.Decimal128;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.util.stream.Collectors;

@RequestScoped
public class PedidoService {

    @Inject
    PedidoRepository pedidoRepository;

    @Inject
    ElasticSearchService elasticSearchService;

    public void pedidoRealizado(final PedidoRealizadoDTO pedidoRealizadoDTO) {
        final Pedido pedido = new Pedido();
        pedido.setCliente(pedidoRealizadoDTO.getCliente());
        pedido.setRestaurante(new Restaurante(pedidoRealizadoDTO.getRestaurante().getNome()));
        pedido.setPratos(pedidoRealizadoDTO.getPratos().stream().map(this::toPrato).collect(Collectors.toList()));

        this.elasticSearchService.index("pedidos", pedidoRealizadoDTO);
        this.pedidoRepository.salvarPedido(pedido);
    }

    private Prato toPrato(final PratoPedidoDTO pratoPedido) {
        return new Prato(pratoPedido.getNome(), pratoPedido.getDescricao(), new Decimal128(pratoPedido.getPreco()));
    }

}
