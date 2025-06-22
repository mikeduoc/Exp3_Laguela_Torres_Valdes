package com.trabajo.spingboot.ecomarket.ecomarket.services;

import java.util.List;
import java.util.Optional;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Cliente;

public interface ClienteService {
    
    List<Cliente> findByAll();

    Optional<Cliente> findById(Long id); //clase java que valida datos

    Cliente save(Cliente unCliente);

    Optional<Cliente> delete (Cliente unCliente);
}
