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

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Cliente;
import com.trabajo.spingboot.ecomarket.ecomarket.services.ClienteServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;


@SpringBootTest
@AutoConfigureMockMvc
public class ClienteRestControllersTest {

@Autowired
private MockMvc mockmvc;

@Autowired
private ObjectMapper objectMapper;

@MockitoBean
private ClienteServiceImpl clienteserviceimpl;
private List<Cliente> clientesLista;

    @Test
    public void verClientesTest() throws Exception{
        when(clienteserviceimpl.findByAll()).thenReturn(clientesLista);
        mockmvc.perform(get("/api/clientes")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verunClienteTest(){
        Cliente unCliente = new Cliente (1L,"bin","bin.laden@gmail.com", "calle falsa 123");
        try{
            when(clienteserviceimpl.findById(1L)).thenReturn(Optional.of(unCliente));
            mockmvc.perform(get("/api/clientes/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error"+ex.getMessage());
        }
    }

    @Test
    public void clienteNoExisteTest() throws Exception{
        when(clienteserviceimpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/clientes/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearClienteTest() throws Exception{
        Cliente unCliente = new Cliente (2L,"ignacio","dos.silas@duocuc.cl", "calle dos 9534");
        Cliente otroCliente = new Cliente (3L,"matias","matii.torres@duocuc.cl", "av siempre viva 77105");
        when(clienteserviceimpl.save(any(Cliente.class))).thenReturn(otroCliente);
        mockmvc.perform(post("/api/clientes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unCliente)))
                .andExpect(status().isCreated());
    }
}
