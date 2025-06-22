package com.trabajo.spingboot.ecomarket.ecomarket.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Proveedor;
import com.trabajo.spingboot.ecomarket.ecomarket.services.ProveedorService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Proveedores", description =  "Operaciones relacionadas con proveedores")
@RestController
@RequestMapping("api/proveedores")
public class ProveedorController {

    @Autowired
    private ProveedorService proveedorservice;

    @Operation(summary = "Obtener lista de proveedores", description = "Devuelve todos los proveedores disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de proveedores retornada correctamente",
                 content = @Content(mediaType = "application/json",
                 schema = @Schema(implementation = Proveedor.class)))

    @GetMapping
    public List<Proveedor> verProveedores(){
        return proveedorservice.findByAll();
    }

    @Operation(summary = "Obtener proveedor por id", description = "Obtener el detalle de un proveedor especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Proveedor encontrado",
                     content = @Content(mediaType = "application/json",schema = @Schema(implementation = Proveedor.class))),
        @ApiResponse (responseCode = "404", description = "Proveedor no encontrado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Proveedor> proveedorOptional = proveedorservice.findById(id);
        if(proveedorOptional.isPresent()){
            return ResponseEntity.ok(proveedorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo proveedor", description = "Crea un proveedor con los datos proporcionados")
    @ApiResponse(responseCode = "201",description = "Proveedor creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Proveedor.class)))
                 
    @PostMapping
    public ResponseEntity<Proveedor> crearProveedor(@RequestBody Proveedor unProveedor) {
        return ResponseEntity.status(HttpStatus.CREATED).body(proveedorservice.save(unProveedor));
    }

    @Operation(summary = "Modifica un proveedor", description = "Modifica un proveedor con la id proporcionada")
    @ApiResponse(responseCode = "201",description = "Proveedor modificado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Proveedor.class)))

    @PutMapping
    public ResponseEntity<?> modificarProveedor(@PathVariable Long id, @RequestBody Proveedor unProveedor) {
        Optional<Proveedor> proOptional = proveedorservice.findById(id);
        if(proOptional.isPresent()){
            Proveedor proveedorexistente = proOptional.get();
            proveedorexistente.setNombre(unProveedor.getNombre());
            proveedorexistente.setEmail(unProveedor.getEmail());
            proveedorexistente.setTelefono(unProveedor.getTelefono());
            proveedorexistente.setDireccion(unProveedor.getDireccion());
            Proveedor proveemodificado = proveedorservice.save(proveedorexistente);
            return ResponseEntity.ok(proveemodificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Elimina un proveedor", description = "Elimina un proveedor con la id proporcionada")
    @ApiResponse(responseCode = "201",description = "Proveedor eliminado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Proveedor.class)))

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProveedor(@PathVariable Long id) {
        Proveedor unProveedor = new Proveedor();
        unProveedor.setId(id);
        Optional<Proveedor> proveedorOptional = proveedorservice.delete(unProveedor);
        if(proveedorOptional.isPresent()){
            return ResponseEntity.ok(proveedorOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
