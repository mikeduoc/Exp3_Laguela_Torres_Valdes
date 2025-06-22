package com.trabajo.spingboot.ecomarket.ecomarket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Producto;
import com.trabajo.spingboot.ecomarket.ecomarket.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productorepository;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByAll(){
        return (List<Producto>) productorepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Producto> delete(Producto unProducto) {
        Optional<Producto> produOptional = productorepository.findById(unProducto.getId());
        produOptional.ifPresent(productoDb ->{
            productorepository.delete(unProducto);
            });
        return produOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Producto> findById(Long id){
        return productorepository.findById(id);
    }

    @Override
    @Transactional
    public Producto save(Producto unProducto) {
        return productorepository.save(unProducto);
    }
    
}
