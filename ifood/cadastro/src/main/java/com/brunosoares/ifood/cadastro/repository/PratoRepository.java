package com.brunosoares.ifood.cadastro.repository;

import com.brunosoares.ifood.cadastro.orm.Prato;
import com.brunosoares.ifood.cadastro.orm.Restaurante;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;
import java.util.List;

@RequestScoped
public class PratoRepository implements PanacheRepository<Prato> {

    public List<Prato> buscarPratosPeloRestaurante(final Restaurante restaurante) {
        return this.list("restaurante", restaurante);
    }

}
