package com.trabajo.spingboot.ecomarket.ecomarket.restcontrollerstest;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Producto;
import com.trabajo.spingboot.ecomarket.ecomarket.services.ProductoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class ProductoRestControllersTest {
    
@Autowired
private MockMvc mockmvc;

@Autowired
private ObjectMapper objectMapper;

@MockitoBean
private ProductoServiceImpl productoserviceimpl;
private List<Producto> productosLista;

    @Test
    public void verProductosTest() throws Exception{
        when(productoserviceimpl.findByAll()).thenReturn(productosLista);
        mockmvc.perform(get("/api/productos")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verunProductoTest(){
        Producto unProducto = new Producto(1L,"lechuga","organica",1500,10);
        try{
            when(productoserviceimpl.findById(1L)).thenReturn(Optional.of(unProducto));
            mockmvc.perform(get("/api/productos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error"+ex.getMessage());
        }
    }

    @Test
    public void productoNoExisteTest() throws Exception{
        when(productoserviceimpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/productos/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearProductoTest() throws Exception{
        Producto unProducto = new Producto (2L,"sal","de mar", 3500,5);
        Producto otroProducto = new Producto (3L,"oregano","entero", 2500,15);
        when(productoserviceimpl.save(any(Producto.class))).thenReturn(otroProducto);
        mockmvc.perform(post("/api/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unProducto)))
                .andExpect(status().isCreated());
    }
}
