package com.brunosoares.ifood.pedido.entity;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@MongoEntity(collection = "pedidos", database = "pedido")
public class Pedido extends PanacheMongoEntity {

    private String cliente;

    private List<Prato> pratos;

    private Restaurante restaurante;

    private String entregador;

    private Localizacao localizacaoEntregador;

}
