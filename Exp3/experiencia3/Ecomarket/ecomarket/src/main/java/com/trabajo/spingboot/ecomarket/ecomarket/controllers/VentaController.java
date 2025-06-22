package com.trabajo.spingboot.ecomarket.ecomarket.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Venta;
import com.trabajo.spingboot.ecomarket.ecomarket.services.VentaService;

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

@Tag(name="Ventas", description =  "Operaciones relacionadas con ventas")
@RestController
@RequestMapping("api/ventas")
public class VentaController {

    @Autowired
    private VentaService ventaservice;

    @Operation(summary = "Obtener lista de ventas", description = "Devuelve todos las ventas realizadas")
    @ApiResponse(responseCode = "200", description = "Lista de ventas retornada correctamente",
                 content = @Content(mediaType = "application/json",
                 schema = @Schema(implementation = Venta.class)))

    @GetMapping
    public List<Venta> verVentas(){
        return ventaservice.findByAll();
    }

    @Operation(summary = "Obtener venta por id", description = "Obtener el detalle de una venta especifica")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Venta encontrada",
                     content = @Content(mediaType = "application/json",schema = @Schema(implementation = Venta.class))),
        @ApiResponse (responseCode = "404", description = "Venta no encontrada")
    })

    @GetMapping("/{id}")
    public ResponseEntity<?> verDetalle(@PathVariable Long id) {
        Optional<Venta> ventaOptional = ventaservice.findById(id);
        if(ventaOptional.isPresent()){
            return ResponseEntity.ok(ventaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Crear una nueva venta", description = "Crea una venta con los datos proporcionados")
    @ApiResponse(responseCode = "201",description = "Venta creada correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class)))
                 
    @PostMapping
    public ResponseEntity<Venta> crearVenta(@RequestBody Venta unVenta) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaservice.save(unVenta));
    }

    @Operation(summary = "Modifica una venta", description = "Modifica una venta con la id proporcionada")
    @ApiResponse(responseCode = "201",description = "Venta modificada correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class)))

    @PutMapping
    public ResponseEntity<?> modificarVenta(@PathVariable Long id, @RequestBody Venta unVenta) {
        Optional<Venta> venOptional = ventaservice.findById(id);
        if(venOptional.isPresent()){
            Venta ventaexistente = venOptional.get();
            ventaexistente.setEmpleado(unVenta.getEmpleado());
            ventaexistente.setSucursal(unVenta.getSucursal());
            ventaexistente.setProducto(unVenta.getProducto());
            ventaexistente.setPrecio(unVenta.getPrecio());
            ventaexistente.setCantidad(unVenta.getCantidad());
            ventaexistente.setMediopago(unVenta.getMediopago());
            Venta ventmodificado = ventaservice.save(ventaexistente);
            return ResponseEntity.ok(ventmodificado);
        }
        return ResponseEntity.notFound().build();
    }

    @Operation(summary = "Elimina una venta", description = "Elimina una venta con la id proporcionada")
    @ApiResponse(responseCode = "201",description = "Venta eliminada correctamente",
                 content = @Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class)))

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVenta(@PathVariable Long id) {
        Venta unVenta = new Venta();
        unVenta.setId(id);
        Optional<Venta> ventaOptional = ventaservice.delete(unVenta);
        if(ventaOptional.isPresent()){
            return ResponseEntity.ok(ventaOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}
