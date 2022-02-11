package com.brunosoares.ifood.cadastro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AtualizarRestauranteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private long id;

    @NotNull
    @Size(max = 120)
    private String nome;

}