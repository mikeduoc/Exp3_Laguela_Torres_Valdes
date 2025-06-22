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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Clientes", description =  "Operaciones relacionadas con clientes")
@RestController
@RequestMapping("api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteservice;

    @Operation(summary = "Obtener lista de clientes", description = "Devuelve todos los clientes disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de clientes retornada correctamente",
                 content = @Content(mediaType = "application/json",
                 schema = @Schema(implementation = Cliente.class)))

    @GetMapping
    public List<Cliente> verClientes(){
        return clienteservice.findByAll();
    }
    
    @Operation(summary = "Obtener cliente por id", description = "Obtener el detalle de un cliente especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado",
                     content = @Content(mediaType = "application/json",schema = @Schema(implementation = Cliente.class))),
        @ApiResponse (responseCode = "404", description = "Cliente no encontrado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Cliente> clienteOptional = clienteservice.findById(id);
        if(clienteOptional.isPresent()){
            return ResponseEntity.ok(clienteOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Crea un cliente con los datos proporcionados")
    @ApiResponse(responseCode = "201",description = "Cliente creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))
                 
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente unCliente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteservice.save(unCliente));
    }

    @Operation(summary = "Modifica un cliente", description = "Modifica un cliente con la id proporcionada")
    @ApiResponse(responseCode = "201",description = "Cliente modificado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))

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
    
    @Operation(summary = "Elimina un cliente", description = "Elimina un cliente con la id proporcionada")
    @ApiResponse(responseCode = "201",description = "Cliente eliminado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)))

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
