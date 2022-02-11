package com.brunosoares.ifood.cadastro.rest;

import com.brunosoares.ifood.cadastro.CadastroIfoodTestLifecycleManager;
import com.brunosoares.ifood.cadastro.RestCommon;
import com.brunosoares.ifood.cadastro.converter.PratoConverter;
import com.brunosoares.ifood.cadastro.dto.AdicionarPratoDTO;
import com.brunosoares.ifood.cadastro.dto.AtualizarPratoDTO;
import com.brunosoares.ifood.cadastro.dto.PratoDTO;
import com.brunosoares.ifood.cadastro.dto.RestauranteDTO;
import com.brunosoares.ifood.cadastro.orm.Prato;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.apachecommons.ReflectionEquals;

import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroIfoodTestLifecycleManager.class)
public class PratoResourceTest {

    @Inject
    PratoConverter pratoConverter;

    @TestSecurity(user = "proprietario1", roles = "proprietario")
    @Test
    @DataSet(value = "pratos-cenario-1.yml", disableConstraints = true)
    public void testBuscarPeloRestaurante() {
        final String res = RestCommon.given()
                .when()
                .pathParam("idRestaurante", 111L)
                .get("/v1/pratos/restaurante/{idRestaurante}")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .asString();
        Approvals.verifyJson(res);
    }

    @TestSecurity(user = "proprietario1", roles = "proprietario")
    @Test
    public void testNovoPrato() {
        final PratoDTO res = RestCommon.given()
                .body(AdicionarPratoDTO.builder()
                        .descricao("descricao")
                        .nome("nome")
                        .preco(new BigDecimal("10.00"))
                        .restaurante(RestauranteDTO.builder()
                                .id(111L)
                                .build())
                        .build())
                .when()
                .post("/v1/pratos")
                .then()
                .statusCode(Response.Status.CREATED.getStatusCode())
                .extract()
                .as(PratoDTO.class);

        Assertions.assertNotNull(res);
        final Prato byId = Prato.findById(res.getId());
        Assertions.assertNotNull(byId);
        Assertions.assertTrue(new ReflectionEquals(this.pratoConverter.toPratoDTO(byId)).matches(res));
    }

    @TestSecurity(user = "proprietario1", roles = "proprietario")
    @Test
    @DataSet(value = "pratos-cenario-1.yml", disableConstraints = true)
    public void testAtualizarPrato() {
        final long id = 123L;
        final PratoDTO res = RestCommon.given()
                .body(AtualizarPratoDTO.builder()
                        .id(id)
                        .nome("nome att")
                        .descricao("desc att")
                        .preco(new BigDecimal("22.00"))
                        .build())
                .when()
                .put("/v1/pratos")
                .then()
                .statusCode(Response.Status.OK.getStatusCode())
                .extract()
                .as(PratoDTO.class);

        Assertions.assertNotNull(res);
        final Prato byId = Prato.findById(id);
        Assertions.assertNotNull(byId);
        Assertions.assertTrue(new ReflectionEquals(this.pratoConverter.toPratoDTO(byId)).matches(res));
    }

    @TestSecurity(user = "proprietario1", roles = "proprietario")
    @Test
    @DataSet(value = "pratos-cenario-1.yml", disableConstraints = true)
    public void testDeletarPrato() {
        final long id = 123L;
        RestCommon.given()
                .pathParam("id", id)
                .when()
                .delete("/v1/pratos/{id}")
                .then()
                .statusCode(Response.Status.NO_CONTENT.getStatusCode());

        final Prato byId = Prato.findById(id);
        Assertions.assertNull(byId);
    }

}