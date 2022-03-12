package com.brunosoares.ifood.pedido.repository;

import com.brunosoares.ifood.pedido.entity.Pedido;

import javax.enterprise.context.RequestScoped;

@RequestScoped
public class PedidoRepository {

    public void salvarPedido(final Pedido pedido) {
        pedido.persist();
    }

}
