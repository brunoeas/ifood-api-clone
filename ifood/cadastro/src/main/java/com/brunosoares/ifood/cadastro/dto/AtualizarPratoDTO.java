package com.brunosoares.ifood.cadastro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AtualizarPratoDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private long id;

    @NotNull
    @Size(min = 3, max = 60)
    private String nome;

    @NotNull
    @Size(min = 3, max = 250)
    private String descricao;

    @NotNull
    private BigDecimal preco;

}
