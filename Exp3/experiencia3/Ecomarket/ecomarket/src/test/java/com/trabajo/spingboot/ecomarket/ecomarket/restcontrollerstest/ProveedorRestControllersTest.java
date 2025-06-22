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

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Proveedor;
import com.trabajo.spingboot.ecomarket.ecomarket.services.ProveedorServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class ProveedorRestControllersTest {
    
@Autowired
private MockMvc mockmvc;

@Autowired
private ObjectMapper objectMapper;

@MockitoBean
private ProveedorServiceImpl proveedorserviceimpl;
private List<Proveedor> proveedoresLista;

    @Test
    public void verProveedoresTest() throws Exception{
        when(proveedorserviceimpl.findByAll()).thenReturn(proveedoresLista);
        mockmvc.perform(get("/api/proveedores")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verunproveedorTest(){
        Proveedor unProveedor = new Proveedor(1L,"alan brito","alan.brito@verdurasdeliciosas.cl","+56988451247","camino lonquen km 30");
        try{
            when(proveedorserviceimpl.findById(1L)).thenReturn(Optional.of(unProveedor));
            mockmvc.perform(get("/api/proveedores/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error"+ex.getMessage());
        }
    }

    @Test
    public void proveedorNoExisteTest() throws Exception{
        when(proveedorserviceimpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/proveedores/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearProveedorTest() throws Exception{
        Proveedor unProveedor = new Proveedor (2L,"aquiles baeza","aquiles.baeza@condimentosagranel.cl","+56910625400","camino a melipilla 3150");
        Proveedor otroProveedor = new Proveedor (3L,"susana oria","susana.oria@frutasorganicas","+56978451122","camino el noviciado 4477");
        when(proveedorserviceimpl.save(any(Proveedor.class))).thenReturn(otroProveedor);
        mockmvc.perform(post("/api/proveedores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unProveedor)))
                .andExpect(status().isCreated());
    }
}
