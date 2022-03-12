package com.brunosoares.ifood.mp.service;

import com.brunosoares.ifood.mp.dto.PratoDTO;
import com.brunosoares.ifood.mp.repository.PratoRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class PratoService {

    @Inject
    PratoRepository pratoRepository;

    public Multi<PratoDTO> buscarTodosPeloRestaurante(final Long idRestaurante) {
        final Uni<RowSet<Row>> query = this.pratoRepository.buscarTodosPeloRestaurante(idRestaurante);

        return this.uniToMulti(query);
    }

    public Multi<PratoDTO> buscarPeloID(final long id) {
        final Uni<RowSet<Row>> query = this.pratoRepository.buscarPeloID(id);

        return this.uniToMulti(query);
    }

    public Multi<PratoDTO> buscarTodos() {
        final Uni<RowSet<Row>> query = this.pratoRepository.buscarTodos();

        return this.uniToMulti(query);
    }

    private Multi<PratoDTO> uniToMulti(final Uni<RowSet<Row>> query) {
        return query.onItem()
                .transformToMulti(RowSet::toMulti)
                .onItem().transform(PratoDTO::from);
    }

}