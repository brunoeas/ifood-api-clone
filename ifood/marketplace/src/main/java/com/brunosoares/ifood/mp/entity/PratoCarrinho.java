package com.brunosoares.ifood.mp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PratoCarrinho {

    private Long prato;

    private String cliente;

}
