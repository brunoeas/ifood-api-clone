package com.brunosoares.ifood.cadastro.rest;

import com.brunosoares.ifood.cadastro.dto.AdicionarRestauranteDTO;
import com.brunosoares.ifood.cadastro.dto.AtualizarRestauranteDTO;
import com.brunosoares.ifood.cadastro.dto.RestauranteDTO;
import com.brunosoares.ifood.cadastro.infra.ConstraintViolationResponse;
import com.brunosoares.ifood.cadastro.service.RestauranteService;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.SimplyTimed;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityRequirement;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@SecurityScheme(
        securitySchemeName = "ifood-oauth",
        type = SecuritySchemeType.OAUTH2,
        flows = @OAuthFlows(password = @OAuthFlow(tokenUrl = "http://localhost:8180/auth/realms/ifood/protocol/openid-connect/token"))
)
@SecurityRequirement(name = "ifood-oauth", scopes = {})
@RolesAllowed("proprietario")
@Path("/v1/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @Inject
    RestauranteService restauranteService;

    @GET
    @APIResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(allOf = RestauranteDTO[].class))
    )
    @Counted(name = "Quantidade buscas Restaurante") // Metrics Prometheus
    @SimplyTimed(name = "Tempo simples de busca") // Metrics Prometheus
    @Timed(name = "Tempo completo de busca") // Metrics Prometheus
    public Response buscarTodos() {
        return Response.ok(this.restauranteService.buscarTodos()).build();
    }

    @POST
    @APIResponse(
        responseCode = "201",
        content = @Content(schema = @Schema(allOf = RestauranteDTO.class))
    )
    @APIResponse(
            responseCode = "400",
            content = @Content(schema = @Schema(allOf = ConstraintViolationResponse.class))
    )
    public Response novoRestaurante(final AdicionarRestauranteDTO dto) {
        return Response.status(Response.Status.CREATED).entity(this.restauranteService.insereRestaurante(dto)).build();
    }

    @PUT
    @APIResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(allOf = RestauranteDTO.class))
    )
    @Counted(name = "Quantidade atualizacoes Restaurante") // Metrics Prometheus
    @SimplyTimed(name = "Tempo simples de atualizacao") // Metrics Prometheus
    @Timed(name = "Tempo completo de atualizacao") // Metrics Prometheus
    public Response atualizaRestaurante(final AtualizarRestauranteDTO dto) {
        return Response.ok(this.restauranteService.atualizaRestaurante(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaRestaurante(@PathParam("id") final Long id) {
        this.restauranteService.deletaRestaurante(id);
        return Response.noContent().build();
    }

}