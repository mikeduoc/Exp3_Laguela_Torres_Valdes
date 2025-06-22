package com.trabajo.spingboot.ecomarket.ecomarket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Venta;
import com.trabajo.spingboot.ecomarket.ecomarket.repository.VentaRepository;

@Service
public class VentaServiceImpl implements VentaService{

    @Autowired
    private VentaRepository ventarepository;

    @Override
    @Transactional(readOnly = true)
    public List<Venta> findByAll(){
        return (List<Venta>) ventarepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Venta> delete(Venta unVenta) {
        Optional<Venta> ventOptional = ventarepository.findById(unVenta.getId());
        ventOptional.ifPresent(ventaDb ->{
            ventarepository.delete(unVenta);
            });
        return ventOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Venta> findById(Long id){
        return ventarepository.findById(id);
    }

    @Override
    @Transactional
    public Venta save(Venta unVenta) {
        return ventarepository.save(unVenta);
    }
}
