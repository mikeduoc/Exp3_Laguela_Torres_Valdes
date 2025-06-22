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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name="Pedidos", description =  "Operaciones relacionadas con pedidos")
@RestController
@RequestMapping("api/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoservice;

    @Operation(summary = "Obtener lista de pedidos", description = "Devuelve todos los pedidos disponibles")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos retornada correctamente",
                 content = @Content(mediaType = "application/json",
                 schema = @Schema(implementation = Pedido.class)))

    @GetMapping
    public List<Pedido> verPedidos(){
        return pedidoservice.findByAll();
    }

    @Operation(summary = "Obtener pedido por id", description = "Obtener el detalle de un pedido especifico")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Pedido encontrado",
                     content = @Content(mediaType = "application/json",schema = @Schema(implementation = Pedido.class))),
        @ApiResponse (responseCode = "404", description = "Pedido no encontrado")
    })

    @GetMapping("/{id}")
    public ResponseEntity<?> PedidoId(@PathVariable Long id) {
        Optional<Pedido> pedidoOptional = pedidoservice.findById(id);
        if(pedidoOptional.isPresent()){
            return ResponseEntity.ok(pedidoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear un nuevo pedido", description = "Crea un pedido con los datos proporcionados")
    @ApiResponse(responseCode = "201",description = "Pedido creado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class)))

    @PostMapping
    public ResponseEntity<Pedido> crearPedido(@RequestBody Pedido unPedido) {
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoservice.save(unPedido));
    }

    @Operation(summary = "Modifica un pedido", description = "Modifica un pedido con la id proporcionada")
    @ApiResponse(responseCode = "201",description = "Pedido modificado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class)))

    @PutMapping
    public ResponseEntity<?> modificarPedido(@PathVariable Long id, @RequestBody Pedido unPedido){
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

    @Operation(summary = "Elimina un pedido", description = "Elimina un pedido con la id proporcionada")
    @ApiResponse(responseCode = "201",description = "Pedido eliminado correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Pedido.class)))

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarPedido(@PathVariable Long id) {
        Pedido unPedido = new Pedido();
        unPedido.setId(id);
        Optional<Pedido> pedidoOptional = pedidoservice.delete(unPedido);
        if(pedidoOptional.isPresent()){
            return ResponseEntity.ok(pedidoOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
