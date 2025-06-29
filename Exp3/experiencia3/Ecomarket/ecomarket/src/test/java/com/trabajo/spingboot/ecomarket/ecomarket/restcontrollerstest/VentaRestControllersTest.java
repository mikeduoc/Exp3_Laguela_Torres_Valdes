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

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Venta;
import com.trabajo.spingboot.ecomarket.ecomarket.services.VentaServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class VentaRestControllersTest {

@Autowired
private MockMvc mockmvc;

@Autowired
private ObjectMapper objectMapper;

@MockitoBean
private VentaServiceImpl ventaserviceimpl;
private List<Venta> ventasLista;

    @Test
    public void verVentasTest() throws Exception{
        when(ventaserviceimpl.findByAll()).thenReturn(ventasLista);
        mockmvc.perform(get("/api/ventas")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verunaventaTest(){
        Venta unVenta = new Venta(1L,"elba lazo","santiago","lechuga hidroponica",1500,1,"debito");
        try{
            when(ventaserviceimpl.findById(1L)).thenReturn(Optional.of(unVenta));
            mockmvc.perform(get("/api/ventas/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error"+ex.getMessage());
        }
    }

    @Test
    public void ventaNoExisteTest() throws Exception{
        when(ventaserviceimpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/ventas/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearVentaTest() throws Exception{
        Venta unVenta = new Venta (2L,"aquiles bailo","antofagasta","sal de mar",3500,2,"credito");
        Venta otroVenta = new Venta (3L,"lola mento","temuco","oregano entero",2500,3,"efectivo");
        when(ventaserviceimpl.save(any(Venta.class))).thenReturn(otroVenta);
        mockmvc.perform(post("/api/ventas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unVenta)))
                .andExpect(status().isCreated());
    }
}
