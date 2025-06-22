package com.trabajo.spingboot.ecomarket.ecomarket.services;

import java.util.List;
import java.util.Optional;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Producto;

public interface ProductoService {

    List<Producto> findByAll();

    Optional<Producto> findById(Long id); //clase java que valida datos

    Producto save(Producto unProducto);

    Optional<Producto> delete (Producto unProducto);
}
