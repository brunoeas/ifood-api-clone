package com.brunosoares.ifood.mp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Restaurante {

    private Long id;

    private String nome;

    private Localizacao localizacao;

}
