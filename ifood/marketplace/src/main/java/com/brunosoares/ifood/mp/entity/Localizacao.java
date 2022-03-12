package com.brunosoares.ifood.mp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Localizacao {

    private Long id;

    private BigDecimal nrLatitude;

    private BigDecimal nrLongitude;

}
