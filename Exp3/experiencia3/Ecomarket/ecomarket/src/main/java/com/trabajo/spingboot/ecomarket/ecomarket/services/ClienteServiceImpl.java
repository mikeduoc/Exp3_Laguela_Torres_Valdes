package com.trabajo.spingboot.ecomarket.ecomarket.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Cliente;
import com.trabajo.spingboot.ecomarket.ecomarket.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    @Autowired
    private ClienteRepository clienterepository;

    @Override
    @Transactional(readOnly = true)
    public List<Cliente> findByAll(){
        return (List<Cliente>) clienterepository.findAll();
    }

    @Override
    @Transactional
    public Optional<Cliente> delete(Cliente unCliente) {
        Optional<Cliente> clientOptional = clienterepository.findById(unCliente.getId());
        clientOptional.ifPresent(clienteDb ->{
            clienterepository.delete(unCliente);
            });
        return clientOptional;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Cliente> findById(Long id){
        return clienterepository.findById(id);
    }

    @Override
    @Transactional
    public Cliente save(Cliente unCliente) {
        return  clienterepository.save(unCliente);
    }
}