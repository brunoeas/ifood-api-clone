package com.brunosoares.ifood.mp.repository;

import com.brunosoares.ifood.mp.entity.PratoCarrinho;
import io.smallrye.mutiny.Uni;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Row;
import io.vertx.mutiny.sqlclient.RowSet;
import io.vertx.mutiny.sqlclient.Tuple;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class PratoCarrinhoRepository {

    @Inject
    PgPool pgPool;

    public Uni<RowSet<Row>> buscarCarrinhoPeloCliente(final String cliente) {
        return this.pgPool.preparedQuery("SELECT * FROM prato_cliente WHERE ds_cliente = $1")
                .execute(Tuple.of(cliente));
    }

    public RowSet<Row> inserir(final PratoCarrinho pratoCarrinho) {
        return this.pgPool.preparedQuery("INSERT INTO prato_cliente(id_prato, ds_cliente) VALUES ($1, $2) RETURNING *")
                .executeAndAwait(Tuple.of(pratoCarrinho.getPrato(), pratoCarrinho.getCliente()));
    }

    public void deletarPeloCliente(final String cliente) {
        this.pgPool.preparedQuery("DELETE FROM prato_cliente WHERE ds_cliente = $1")
                .executeAndAwait(Tuple.of(cliente));
    }
}
