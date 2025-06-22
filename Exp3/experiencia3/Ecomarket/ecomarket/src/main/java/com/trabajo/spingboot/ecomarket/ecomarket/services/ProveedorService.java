package com.trabajo.spingboot.ecomarket.ecomarket.services;

import java.util.List;
import java.util.Optional;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Proveedor;

public interface ProveedorService {

    List<Proveedor> findByAll();

    Optional<Proveedor> findById(Long id); //clase java que valida datos

    Proveedor save(Proveedor unProveedor);

    Optional<Proveedor> delete (Proveedor unProveedor);
}
