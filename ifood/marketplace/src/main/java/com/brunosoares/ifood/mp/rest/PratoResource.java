package com.brunosoares.ifood.mp.rest;

import com.brunosoares.ifood.mp.dto.PratoDTO;
import com.brunosoares.ifood.mp.service.PratoService;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/v1/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

    @Inject
    PratoService pratoService;

    @GET
    @Path("/restaurante/{idRestaurante}")
    @APIResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(allOf = PratoDTO[].class))
    )
    public Multi<PratoDTO> buscarTodosPeloRestaurante(@PathParam("idRestaurante") final Long idRestaurante) {
        return this.pratoService.buscarTodosPeloRestaurante(idRestaurante);
    }

    @GET
    @APIResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(allOf = PratoDTO[].class))
    )
    public Multi<PratoDTO> buscarTodos() {
        return this.pratoService.buscarTodos();
    }

}
