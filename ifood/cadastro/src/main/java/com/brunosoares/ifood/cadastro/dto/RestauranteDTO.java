package com.brunosoares.ifood.cadastro.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RestauranteDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private long id;

    @NotNull
    @Size(max = 120)
    private String proprietario;

    @NotNull
    @Size(min = 18, max = 18)
    @CNPJ
    private String nrCnpj;

    @NotNull
    @Size(max = 120)
    private String nome;

    @NotNull
    private LocalizacaoDTO localizacao;

    @NotNull
    private LocalDateTime dtCriacao;

    @NotNull
    private LocalDateTime dtAtualizacao;

}
