package com.brunosoares.ifood.pedido.rest;

import com.brunosoares.ifood.pedido.dto.PedidoRealizadoDTO;
import com.brunosoares.ifood.pedido.service.PedidoService;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.jboss.logmanager.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class PedidoRealizadoIncoming {

    @Inject
    PedidoService pedidoService;

    @Incoming("pedidos")
    public void pedidoRealizado(final PedidoRealizadoDTO dto) {
        this.pedidoService.pedidoRealizado(dto);
        Logger.getAnonymousLogger().info("pedidoRealizado - DTO: " + dto);
    }

}
