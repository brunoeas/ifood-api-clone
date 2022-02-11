package com.brunosoares.ifood.cadastro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class LocalizacaoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private BigDecimal nrLatitude;

    @NotNull
    private BigDecimal nrLongitude;

}
