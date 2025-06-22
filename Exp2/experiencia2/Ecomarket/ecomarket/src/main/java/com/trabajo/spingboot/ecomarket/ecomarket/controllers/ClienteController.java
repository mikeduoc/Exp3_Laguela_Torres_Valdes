package com.trabajo.spingboot.ecomarket.ecomarket.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Cliente;
import com.trabajo.spingboot.ecomarket.ecomarket.services.ClienteService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteservice;

    @GetMapping
    public List<Cliente> List() {
        return clienteservice.findByAll();
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = clienteservice.findById(id);
        if(clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente unCliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteservice.save(unCliente));
    }

    @PutMapping
    public ResponseEntity<?> modificarCliente(@PathVariable Long id, @RequestBody Cliente unCliente) {
        Optional<Cliente> cliOptional = clienteservice.findById(id);
        if(cliOptional.isPresent()){
            Cliente clienteexistente = cliOptional.get();
            clienteexistente.setNombre(unCliente.getNombre());
            clienteexistente.setDireccion(unCliente.getDireccion());
            clienteexistente.setEmail(unCliente.getEmail());
            Cliente clientmodificado = clienteservice.save(clienteexistente);
            return ResponseEntity.ok(clientmodificado);
        }
        return ResponseEntity.notFound().build();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        Cliente unCliente = new Cliente();
        unCliente.setId(id);
        Optional<Cliente> clienteOptional = clienteservice.delete(unCliente);
        if(clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
