package com.brunosoares.ifood.cadastro.rest;

import com.brunosoares.ifood.cadastro.orm.Prato;
import com.brunosoares.ifood.cadastro.service.PratoService;

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

@Path("/v1/pratos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PratoResource {

    @Inject
    PratoService pratoService;

    @GET
    @Path("/restaurante/{idRestaurante}")
    public Response buscarTodosPeloRestaurante(@PathParam("idRestaurante") final Long idRestaurante) {
        return Response.ok(this.pratoService.buscarTodosPeloRestaurante(idRestaurante)).build();
    }

    @POST
    public Response novoPrato(final Prato dto) {
        return Response.ok(this.pratoService.inserePrato(dto)).build();
    }

    @PUT
    public Response atualizaPrato(final Prato dto) {
        return Response.ok(this.pratoService.atualizaPrato(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaPrato(@PathParam("id") final Long id) {
        this.pratoService.deletaPrato(id);
        return Response.ok().build();
    }

}