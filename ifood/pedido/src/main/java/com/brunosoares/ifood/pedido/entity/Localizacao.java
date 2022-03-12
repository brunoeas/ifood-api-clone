package com.brunosoares.ifood.pedido.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Decimal128;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Localizacao {

    private Decimal128 nrLatitude;

    private Decimal128 nrLongitude;

}
