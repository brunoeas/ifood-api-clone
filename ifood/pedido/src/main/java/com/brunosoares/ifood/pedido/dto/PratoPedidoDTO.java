package com.brunosoares.ifood.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PratoPedidoDTO {

    @NotNull
    private String nome;

    @NotNull
    private String descricao;

    @NotNull
    private BigDecimal preco;

}
