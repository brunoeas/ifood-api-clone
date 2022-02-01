package com.brunosoares.ifood.pedido;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PedidoResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/v1/pedido")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

}