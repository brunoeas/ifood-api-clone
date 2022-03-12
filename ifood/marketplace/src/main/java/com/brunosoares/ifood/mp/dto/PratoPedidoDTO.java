package com.brunosoares.ifood.mp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PratoPedidoDTO {

    @NotNull
    @Size(min = 3, max = 60)
    private String nome;

    @NotNull
    @Size(min = 3, max = 250)
    private String descricao;

    @NotNull
    private BigDecimal preco;

}
