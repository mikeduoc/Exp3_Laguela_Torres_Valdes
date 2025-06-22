package com.trabajo.spingboot.ecomarket.ecomarket.repository;

import org.springframework.data.repository.CrudRepository;
import com.trabajo.spingboot.ecomarket.ecomarket.entities.Producto;

public interface ProductoRepository extends CrudRepository<Producto, Long> {

    
}
