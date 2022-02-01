package com.brunosoares.ifood.cadastro.service;

import com.brunosoares.ifood.cadastro.converter.PratoConverter;
import com.brunosoares.ifood.cadastro.dto.AdicionarPratoDTO;
import com.brunosoares.ifood.cadastro.dto.AtualizarPratoDTO;
import com.brunosoares.ifood.cadastro.dto.PratoDTO;
import com.brunosoares.ifood.cadastro.orm.Prato;
import com.brunosoares.ifood.cadastro.orm.Restaurante;
import com.brunosoares.ifood.cadastro.repository.PratoRepository;
import com.brunosoares.ifood.cadastro.repository.RestauranteRepository;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@RequestScoped
public class PratoService {

    @Inject
    PratoRepository pratoRepository;

    @Inject
    RestauranteRepository restauranteRepository;

    @Inject
    PratoConverter pratoConverter;

    public List<PratoDTO> buscarTodosPeloRestaurante(final Long idRestaurante) {
        final Restaurante restaurante = this.restauranteRepository.findByIdOptional(idRestaurante)
                .orElseThrow(() -> new NotFoundException("Restaurante não existe"));
        final List<Prato> ormList = this.pratoRepository.buscarPratosPeloRestaurante(restaurante);
        return ormList.stream()
                .map(this.pratoConverter::toPratoDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public PratoDTO inserePrato(@Valid final AdicionarPratoDTO dto) {
        if (dto.getRestaurante() == null) {
            throw new NotFoundException("ID do restaurante inexistente");
        }

        final Prato orm = this.pratoConverter.toPratoORM(dto);
        orm.setId(null);

        final Restaurante restaurante = this.restauranteRepository.findByIdOptional(dto.getRestaurante().getId())
                .orElseThrow(() -> new NotFoundException("ID do restaurante inexistente"));
        orm.setRestaurante(restaurante);

        this.pratoRepository.persist(orm);
        return this.pratoConverter.toPratoDTO(orm);
    }

    @Transactional
    public PratoDTO atualizaPrato(@Valid final AtualizarPratoDTO dto) {
        final Prato orm = this.pratoRepository.findByIdOptional(dto.getId())
                .orElseThrow(() -> new NotFoundException("Id inexistente"));
        this.pratoConverter.toPratoORM(dto, orm);
        this.pratoRepository.persist(orm);
        return this.pratoConverter.toPratoDTO(orm);
    }

    @Transactional
    public void deletaPrato(final Long id) {
        final Prato orm = this.pratoRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Id inexistente"));
        this.pratoRepository.delete(orm);
    }

}