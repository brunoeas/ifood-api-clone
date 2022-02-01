package com.brunosoares.ifood.cadastro.repository;

import com.brunosoares.ifood.cadastro.orm.Restaurante;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class RestauranteRepository implements PanacheRepository<Restaurante> {
}
