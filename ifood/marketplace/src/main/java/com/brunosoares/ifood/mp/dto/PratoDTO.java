package com.brunosoares.ifood.mp.dto;

import io.vertx.mutiny.sqlclient.Row;
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
public class PratoDTO {

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

    public static PratoDTO from(final Row row) {
        return PratoDTO.builder()
                .id(row.getLong("id_prato"))
                .nome(row.getString("ds_nome"))
                .descricao(row.getString("ds_descricao"))
                .preco(row.getBigDecimal("nr_preco"))
                .build();
    }

}
