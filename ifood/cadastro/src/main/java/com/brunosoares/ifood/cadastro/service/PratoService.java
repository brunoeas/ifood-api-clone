package com.brunosoares.ifood.cadastro.service;

import com.brunosoares.ifood.cadastro.orm.Prato;
import com.brunosoares.ifood.cadastro.orm.Restaurante;
import com.brunosoares.ifood.cadastro.repository.PratoRepository;
import com.brunosoares.ifood.cadastro.repository.RestauranteRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.List;

@RequestScoped
public class PratoService {

    @Inject
    PratoRepository pratoRepository;

    @Inject
    RestauranteRepository restauranteRepository;

    public List<Prato> buscarTodosPeloRestaurante(final Long idRestaurante) {
        final Restaurante restaurante = this.restauranteRepository.findByIdOptional(idRestaurante)
                .orElseThrow(() -> new NotFoundException("Restaurante não existe"));
        return this.pratoRepository.buscarPratosPeloRestaurante(restaurante);
    }

    @Transactional
    public Prato inserePrato(final Prato dto) {
        if (dto.getRestaurante() == null || dto.getRestaurante().getId() == null) {
            throw new NotFoundException("ID do restaurante inexistente");
        }
        final Restaurante restaurante = this.restauranteRepository.findByIdOptional(dto.getRestaurante().getId())
                .orElseThrow(() -> new NotFoundException("ID do restaurante inexistente"));
        dto.setRestaurante(restaurante);

        this.pratoRepository.persist(dto);
        return dto;
    }

    @Transactional
    public Prato atualizaPrato(final Prato dto) {
        final Prato orm = this.pratoRepository.findByIdOptional(dto.getId())
                .orElseThrow(() -> new NotFoundException("Id inexistente"));
        orm.setNome(dto.getNome());
        orm.setDescricao(dto.getDescricao());
        orm.setPreco(dto.getPreco());
        this.pratoRepository.persist(orm);
        return orm;
    }

    @Transactional
    public void deletaPrato(final Long id) {
        final Prato orm = this.pratoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Id inexistente"));
        this.pratoRepository.delete(orm);
    }

}