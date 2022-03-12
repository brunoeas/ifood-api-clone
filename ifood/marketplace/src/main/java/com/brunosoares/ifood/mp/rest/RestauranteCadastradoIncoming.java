package com.brunosoares.ifood.mp.rest;

import com.brunosoares.ifood.mp.service.RestauranteService;
import org.eclipse.microprofile.reactive.messaging.Incoming;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class RestauranteCadastradoIncoming {

    @Inject
    RestauranteService restauranteService;

    @Incoming("restaurantes")
    public void recebeNovoRestaurante(final String json) {
        this.restauranteService.insereNovoRestaurante(json);
    }

}
