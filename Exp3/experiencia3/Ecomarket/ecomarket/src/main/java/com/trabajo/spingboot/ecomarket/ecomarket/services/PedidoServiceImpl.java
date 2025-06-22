package com.trabajo.spingboot.ecomarket.ecomarket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Pedido;
import com.trabajo.spingboot.ecomarket.ecomarket.repository.PedidoRepository;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    private PedidoRepository pedidorepository;

    @Override
    @Transactional(readOnly = true)
    public List<Pedido> findByAll(){
        return (List<Pedido>) pedidorepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Pedido> delete(Pedido unPedido) {
        Optional<Pedido> pedidOptional = pedidorepository.findById(unPedido.getId());
        pedidOptional.ifPresent(pedidoDb ->{
            pedidorepository.delete(unPedido);
            });
        return pedidOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Pedido> findById(Long id){
        return pedidorepository.findById(id);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Pedido save(Pedido unPedido){
        return pedidorepository.save(unPedido);
    }
    
}
