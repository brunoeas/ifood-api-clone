package com.brunosoares.ifood.mp.rest;

import com.brunosoares.ifood.mp.dto.PratoCarrinhoDTO;
import com.brunosoares.ifood.mp.service.CarrinhoService;
import io.smallrye.mutiny.Multi;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/v1/carrinho")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarrinhoResource {

    @Inject
    CarrinhoService carrinhoService;

    @GET
    @APIResponse(
            responseCode = "200",
            content = @Content(schema = @Schema(allOf = PratoCarrinhoDTO[].class))
    )
    public Multi<PratoCarrinhoDTO> buscarCarrinhoPeloCliente() {
        return this.carrinhoService.buscarCarrinhoPeloCliente();
    }

    @POST
    @Path("/prato/{idPrato}")
    @APIResponse(
            responseCode = "201",
            content = @Content(schema = @Schema(allOf = PratoCarrinhoDTO.class))
    )
    public Response adicionarPrato(@PathParam("idPrato") final Long idPrato) {
        return Response.status(Response.Status.CREATED).entity(this.carrinhoService.adicionarPrato(idPrato)).build();
    }

    @POST
    @Path("/realizar-pedido")
    public Response finalizarPedido() {
        this.carrinhoService.finalizarPedido();
        return Response.noContent().build();
    }

}
