package com.brunosoares.ifood.cadastro.service;

import com.brunosoares.ifood.cadastro.converter.RestauranteConverter;
import com.brunosoares.ifood.cadastro.dto.AdicionarRestauranteDTO;
import com.brunosoares.ifood.cadastro.dto.AtualizarRestauranteDTO;
import com.brunosoares.ifood.cadastro.dto.RestauranteDTO;
import com.brunosoares.ifood.cadastro.orm.Restaurante;
import com.brunosoares.ifood.cadastro.repository.RestauranteRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class RestauranteService {

    @Inject
    RestauranteRepository restauranteRepository;

    @Inject
    RestauranteConverter restauranteConverter;

    public List<RestauranteDTO> buscarTodos() {
        return this.restauranteRepository.listAll()
                .stream()
                .map(this.restauranteConverter::toRestauranteDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public RestauranteDTO insereRestaurante(@Valid final AdicionarRestauranteDTO dto) {
        final Restaurante orm = this.restauranteConverter.toRestauranteORM(dto);
        this.restauranteRepository.persist(orm);
        return this.restauranteConverter.toRestauranteDTO(orm);
    }

    @Transactional
    public RestauranteDTO atualizaRestaurante(@Valid final AtualizarRestauranteDTO dto) {
        final Restaurante orm = this.restauranteRepository.findByIdOptional(dto.getId())
                .orElseThrow(() -> new NotFoundException("Id inexistente"));
        orm.setNome(dto.getNome());
        this.restauranteConverter.toRestauranteORM(dto, orm);
        this.restauranteRepository.persist(orm);
        return this.restauranteConverter.toRestauranteDTO(orm);
    }

    @Transactional
    public void deletaRestaurante(final Long id) {
        final Restaurante orm = this.restauranteRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Id inexistente"));
        this.restauranteRepository.delete(orm);
    }

}