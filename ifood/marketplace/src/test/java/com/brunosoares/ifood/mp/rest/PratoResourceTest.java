package com.brunosoares.ifood.mp.rest;

import com.brunosoares.ifood.mp.RestCommon;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.Response;

@QuarkusTest
class PratoResourceTest {

    @Test
    void testBuscarPeloRestaurante() {
        RestCommon.given()
                .when()
                .pathParam("idRestaurante", 111L)
                .get("/v1/pratos/restaurante/{idRestaurante}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @Test
    void testBuscarTodos() {
        RestCommon.given()
                .when()
                .get("/v1/pratos")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

}