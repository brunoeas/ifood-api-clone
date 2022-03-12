package com.brunosoares.ifood.pedido.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PedidoRealizadoDTO {

    private List<PratoPedidoDTO> pratos;

    private RestauranteDTO restaurante;

    private String cliente;

}
