package com.trabajo.spingboot.ecomarket.ecomarket.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Pedido;
import com.trabajo.spingboot.ecomarket.ecomarket.services.PedidoService;

@RestController
@RequestMapping("api/pedido")
public class PedidoController {

    @Autowired
    private PedidoService pedidoservice;

    @GetMapping
    public List<Pedido> List() {
        return pedidoservice.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> PedidoId(@PathVariable Long id) {
        Optional<Pedido> pedidoOptional = pedidoservice.findById(id);
        if(pedidoOptional.isPresent()){
            return ResponseEntity.ok(pedidoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Pedido> crear(@RequestBody Pedido unPedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoservice.save(unPedido));
    }

    @PutMapping
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Pedido unPedido){
        Optional<Pedido> pediOptional = pedidoservice.findById(id);
        if(pediOptional.isPresent()){
            Pedido pedidoexistente = pediOptional.get();
            pedidoexistente.setFechaCreacion(unPedido.getFechaCreacion());
            pedidoexistente.setEstado(unPedido.getEstado());
            pedidoexistente.setDireccionEnvio(unPedido.getDireccionEnvio());
            Pedido pedidomodificado = pedidoservice.save(pedidoexistente);
            return ResponseEntity.ok(pedidomodificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Pedido unPedido = new Pedido();
        unPedido.setId(id);
        Optional<Pedido> pedidoOptional = pedidoservice.delete(unPedido);
        if(pedidoOptional.isPresent()){
            return ResponseEntity.ok(pedidoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
