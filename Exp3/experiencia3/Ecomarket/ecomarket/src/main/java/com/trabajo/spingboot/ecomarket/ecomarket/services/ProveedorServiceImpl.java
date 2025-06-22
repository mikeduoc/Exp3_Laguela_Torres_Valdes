package com.trabajo.spingboot.ecomarket.ecomarket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Proveedor;
import com.trabajo.spingboot.ecomarket.ecomarket.repository.ProveedorRepository;

@Service
public class ProveedorServiceImpl implements ProveedorService{

    @Autowired
    private ProveedorRepository proveedorrepository;

    @Override
    @Transactional(readOnly = true)
    public List<Proveedor> findByAll(){
        return (List<Proveedor>) proveedorrepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Proveedor> delete(Proveedor unProveedor) {
        Optional<Proveedor> proveeOptional = proveedorrepository.findById(unProveedor.getId());
        proveeOptional.ifPresent(proveedorDb ->{
            proveedorrepository.delete(unProveedor);
            });
        return proveeOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Proveedor> findById(Long id){
        return proveedorrepository.findById(id);
    }

    @Override
    @Transactional
    public Proveedor save(Proveedor unProveedor) {
        return proveedorrepository.save(unProveedor);
    }
}
