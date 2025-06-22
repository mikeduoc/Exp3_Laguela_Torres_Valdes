package com.trabajo.spingboot.ecomarket.ecomarket.services;

import java.util.List;
import java.util.Optional;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Pedido;

public interface PedidoService {

    List<Pedido> findByAll();

    Optional<Pedido> findById(Long id); //clase java que valida datos

    Pedido save(Pedido unPedido);

    Optional<Pedido> delete (Pedido unPedido);
}
