package com.brunosoares.ifood.mp.service;

import com.brunosoares.ifood.mp.entity.Restaurante;
import com.brunosoares.ifood.mp.repository.RestauranteRepository;
import com.google.gson.Gson;
import org.jboss.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@RequestScoped
public class RestauranteService {

    private static final Logger LOGGER = Logger.getLogger(RestauranteService.class.getName());

    @Inject
    RestauranteRepository restauranteRepository;

    public void insereNovoRestaurante(final String json) {
        LOGGER.info("Novo Restaurante JSON recebido: " + json);
        final Restaurante restaurante = new Gson().fromJson(json, Restaurante.class);
        this.restauranteRepository.inserir(restaurante);
        LOGGER.info("Novo Restaurante inserido: " + restaurante.getNome());
    }

}
