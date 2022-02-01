package com.brunosoares.ifood.cadastro.converter;

import com.brunosoares.ifood.cadastro.dto.AdicionarPratoDTO;
import com.brunosoares.ifood.cadastro.dto.AtualizarPratoDTO;
import com.brunosoares.ifood.cadastro.dto.PratoDTO;
import com.brunosoares.ifood.cadastro.orm.Prato;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface PratoConverter {

    Prato toPratoORM(final AdicionarPratoDTO dto);

    void toPratoORM(final AtualizarPratoDTO dto, @MappingTarget final Prato orm);

    PratoDTO toPratoDTO(final Prato orm);

}
