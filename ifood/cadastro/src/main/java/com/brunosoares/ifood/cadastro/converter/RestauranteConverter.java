package com.brunosoares.ifood.cadastro.converter;

import com.brunosoares.ifood.cadastro.dto.AdicionarRestauranteDTO;
import com.brunosoares.ifood.cadastro.dto.AtualizarRestauranteDTO;
import com.brunosoares.ifood.cadastro.dto.RestauranteDTO;
import com.brunosoares.ifood.cadastro.orm.Restaurante;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface RestauranteConverter  {

    Restaurante toRestauranteORM(final AdicionarRestauranteDTO dto);

    void toRestauranteORM(final AtualizarRestauranteDTO dto, @MappingTarget final Restaurante orm);

    @Mapping(target = "dtCriacao", dateFormat = "dd/MM/yyyy HH:mm:ss")
    @Mapping(target = "dtAtualizacao", dateFormat = "dd/MM/yyyy HH:mm:ss")
    RestauranteDTO toRestauranteDTO(final Restaurante orm);

}
