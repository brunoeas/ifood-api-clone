package com.brunosoares.ifood.cadastro.rest;

import com.brunosoares.ifood.cadastro.CadastroIfoodTestLifecycleManager;
import com.brunosoares.ifood.cadastro.RestCommon;
import com.brunosoares.ifood.cadastro.converter.RestauranteConverter;
import com.brunosoares.ifood.cadastro.dto.AdicionarRestauranteDTO;
import com.brunosoares.ifood.cadastro.dto.AtualizarRestauranteDTO;
import com.brunosoares.ifood.cadastro.dto.LocalizacaoDTO;
import com.brunosoares.ifood.cadastro.dto.RestauranteDTO;
import com.brunosoares.ifood.cadastro.orm.Restaurante;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroIfoodTestLifecycleManager.class)
class RestauranteResourceTest {

    @Inject
    RestauranteConverter restauranteConverter;

    @TestSecurity(user = "proprietario1", roles = "proprietario")
    @Test
    @Order(1)
    @DataSet(value = "restaurantes-cenario-1.yml", disableConstraints = true)
    void testBuscarTodos() {
        RestCommon.given()
                .when()
                .get("/v1/restaurantes")
                .then()
                .statusCode(Response.Status.OK.getStatusCode());
    }

    @TestSecurity(user = "proprietario1", roles = "proprietario")
    @Order(2)
    @Test
    void testNovoRestaurante() {
        final RestauranteDTO res = RestCommon.given()
                .body(AdicionarRestauranteDTO.builder()
                        .proprietario("eu mesmo")
                        .nome("brunão")
                        .nrCnpj("94.955.237/0001-70")
                        .localizacao(LocalizacaoDTO.builder()
                                .nrLatitude(new BigDecimal("10.00"))
                                .nrLongitude(new BigDecimal("10.00"))
                                .build())
                        .build())
                .when()
                .post("/v1/restaurantes")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract()
                .as(RestauranteDTO.class);

        Assertions.assertNotNull(res);
        final Restaurante byId = Restaurante.findById(res.getId());
        Assertions.assertNotNull(byId);
        Assertions.assertTrue(new ReflectionEquals(this.restauranteConverter.toRestauranteDTO(byId),  "dtCriacao", "dtAtualizacao")
                .matches(res));
    }

    @TestSecurity(user = "proprietario1", roles = "proprietario")
    @Order(3)
    @Test
    @DataSet(value = "restaurantes-cenario-1.yml", disableConstraints = true)
    void testAtualizarRestaurante() {
        final long id = 123L;
        final RestauranteDTO res = RestCommon.given()
                .body(AtualizarRestauranteDTO.builder()
                        .id(id)
                        .nome("restaurante att")
                        .build())
                .when()
                .put("/v1/restaurantes")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(RestauranteDTO.class);

        Assertions.assertNotNull(res);
        final Restaurante byId = Restaurante.findById(id);
        Assertions.assertNotNull(byId);
        Assertions.assertTrue(new ReflectionEquals(this.restauranteConverter.toRestauranteDTO(byId), "dtAtualizacao")
                .matches(res));
    }

    @TestSecurity(user = "proprietario1", roles = "proprietario")
    @Order(4)
    @Test
    @DataSet(value = "restaurantes-cenario-1.yml", disableConstraints = true)
    void testDeletarRestaurante() {
        final long id = 123L;
        RestCommon.given()
                .pathParam("id", id)
                .when()
                .delete("/v1/restaurantes/{id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        final Restaurante byId = Restaurante.findById(id);
        Assertions.assertNull(byId);
    }

}