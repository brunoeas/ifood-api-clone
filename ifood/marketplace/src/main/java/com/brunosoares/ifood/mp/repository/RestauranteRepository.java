package com.brunosoares.ifood.mp.repository;

import com.brunosoares.ifood.mp.entity.Restaurante;
import io.vertx.mutiny.pgclient.PgPool;
import io.vertx.mutiny.sqlclient.Tuple;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class RestauranteRepository {

    @Inject
    PgPool pgPool;

    public void inserir(final Restaurante restaurante) {
        this.pgPool.preparedQuery("INSERT INTO localizacao(id_localizacao, nr_latitude, nr_longitude) VALUES ($1, $2, $3)")
                .executeAndAwait(Tuple.of(restaurante.getLocalizacao().getId(), restaurante.getLocalizacao().getNrLatitude(),
                        restaurante.getLocalizacao().getNrLongitude()));

        this.pgPool.preparedQuery("INSERT INTO restaurante(id_restaurante, ds_nome, id_localizacao) VALUES ($1, $2, $3)")
                .executeAndAwait(Tuple.of(restaurante.getId(), restaurante.getNome(), restaurante.getLocalizacao().getId()));
    }

}
