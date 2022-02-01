package com.brunosoares.ifood.cadastro.rest;

import com.brunosoares.ifood.cadastro.CadastroIfoodTestLifecycleManager;
import com.github.database.rider.cdi.api.DBRider;
import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.configuration.Orthography;
import com.github.database.rider.core.api.dataset.DataSet;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;

@DBRider
@DBUnit(caseInsensitiveStrategy = Orthography.LOWERCASE)
@QuarkusTest
@QuarkusTestResource(CadastroIfoodTestLifecycleManager.class)
public class RestauranteResourceTest {

    @Test
    @DataSet("restaurantes-cenario-1.yml")
    public void testBuscarTodos() {
        final String res = given()
                .when()
                .get("/v1/restaurantes")
                .then()
                .statusCode(200)
                .extract()
                .asString();
        Approvals.verifyJson(res);
    }

}