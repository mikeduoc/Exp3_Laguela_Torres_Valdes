package com.trabajo.spingboot.ecomarket.ecomarket.services;

import java.util.List;
import java.util.Optional;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Venta;

public interface VentaService {

    List<Venta> findByAll();

    Optional<Venta> findById(Long id); //clase java que valida datos

    Venta save(Venta unVenta);

    Optional<Venta> delete (Venta unVenta);
}
