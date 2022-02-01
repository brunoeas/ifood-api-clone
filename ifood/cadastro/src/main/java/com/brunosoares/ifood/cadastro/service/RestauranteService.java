package com.brunosoares.ifood.cadastro.service;

import com.brunosoares.ifood.cadastro.orm.Restaurante;
import com.brunosoares.ifood.cadastro.repository.RestauranteRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@RequestScoped
public class RestauranteService {

    @Inject
    RestauranteRepository restauranteRepository;

    public List<Restaurante> buscarTodos() {
        return this.restauranteRepository.listAll();
    }

    @Transactional
    public Restaurante insereRestaurante(final Restaurante dto) {
        this.restauranteRepository.persist(dto);
        return dto;
    }

    @Transactional
    public Restaurante atualizaRestaurante(final Restaurante dto) {
        final Restaurante orm = this.restauranteRepository.findByIdOptional(dto.getId())
                .orElseThrow(() -> new NotFoundException("Id inexistente"));
        orm.setNome(dto.getNome());
        this.restauranteRepository.persist(orm);
        return orm;
    }

    @Transactional
    public void deletaRestaurante(final Long id) {
        final Restaurante orm = this.restauranteRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Id inexistente"));
        this.restauranteRepository.delete(orm);
    }

}