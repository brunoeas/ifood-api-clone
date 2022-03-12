package com.brunosoares.ifood.mp.repository;

import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class PratoRepository {

    @Inject
    PgPool pgPool;

    public Uni<RowSet<Row>> buscarTodosPeloRestaurante(final Long idRestaurante) {
        return this.pgPool.preparedQuery("SELECT * FROM prato WHERE id_restaurante = $1")
                .execute(Tuple.of(idRestaurante));
    }

    public Uni<RowSet<Row>> buscarPeloID(final long id) {
        return this.pgPool.preparedQuery("SELECT * FROM prato WHERE id_prato = $1")
                .execute(Tuple.of(id));
    }

    public Uni<RowSet<Row>> buscarTodos() {
        return this.pgPool.preparedQuery("SELECT * FROM prato").execute();
    }

}
