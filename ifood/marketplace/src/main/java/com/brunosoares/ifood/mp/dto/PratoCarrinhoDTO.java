package com.brunosoares.ifood.mp.dto;

import io.vertx.mutiny.sqlclient.Row;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PratoCarrinhoDTO {

    @NotNull
    private Long prato;

    @NotNull
    private String cliente;

    public static PratoCarrinhoDTO from(final Row row) {
        return PratoCarrinhoDTO.builder()
                .prato(row.getLong("id_prato"))
                .cliente(row.getString("ds_cliente"))
                .build();
    }

}
