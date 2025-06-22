package com.trabajo.spingboot.ecomarket.ecomarket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
import java.util.Optional;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Producto;
import com.trabajo.spingboot.ecomarket.ecomarket.services.ProductoService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("api/productos")
public class ProductoController {


    @Autowired
    private ProductoService productoservice;

    @GetMapping
    public List<Producto> List(){
        return productoservice.findByAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id){
        Optional<Producto> productoOptional = productoservice.findById(id);
        if(productoOptional.isPresent()){
            return ResponseEntity.ok(productoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Producto> crear(@RequestBody Producto unProducto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoservice.save(unProducto));
    }

    @PutMapping
    public ResponseEntity<?> modificar(@PathVariable Long id, @RequestBody Producto unProducto) {
        Optional<Producto> producOptional = productoservice.findById(id);
        if(producOptional.isPresent()){
            Producto productoexistente = producOptional.get();
            productoexistente.setNombre(unProducto.getNombre());
            productoexistente.setDescripcion(unProducto.getDescripcion());
            productoexistente.setPrecio(unProducto.getPrecio());
            productoexistente.setStock(unProducto.getStock());
            Producto productomodificado = productoservice.save(productoexistente);
            return ResponseEntity.ok(productomodificado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> elinimar(@PathVariable Long id){
        Producto unProducto = new Producto();
        unProducto.setId(id);
        Optional<Producto> productoOptional = productoservice.delete(unProducto);
        if(productoOptional.isPresent()){
            return ResponseEntity.ok(productoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
    
}
