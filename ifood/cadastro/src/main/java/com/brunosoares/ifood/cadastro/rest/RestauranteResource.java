package com.brunosoares.ifood.cadastro.rest;

import com.brunosoares.ifood.cadastro.orm.Restaurante;
import com.brunosoares.ifood.cadastro.service.PratoService;
import com.brunosoares.ifood.cadastro.service.RestauranteService;

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

@Path("/v1/restaurantes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestauranteResource {

    @Inject
    RestauranteService restauranteService;

    @GET
    public Response buscarTodos() {
        return Response.ok(this.restauranteService.buscarTodos()).build();
    }

    @POST
    public Response novoRestaurante(final Restaurante dto) {
        return Response.ok(this.restauranteService.insereRestaurante(dto)).build();
    }

    @PUT
    public Response atualizaRestaurante(final Restaurante dto) {
        return Response.ok(this.restauranteService.atualizaRestaurante(dto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletaRestaurante(@PathParam("id") final Long id) {
        this.restauranteService.deletaRestaurante(id);
        return Response.ok().build();
    }

}