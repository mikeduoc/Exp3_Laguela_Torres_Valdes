package com.trabajo.spingboot.ecomarket.ecomarket.restcontrollerstest;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Pedido;
import com.trabajo.spingboot.ecomarket.ecomarket.services.PedidoServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@AutoConfigureMockMvc
public class PedidoRestControllersTest {

@Autowired
private MockMvc mockmvc;

@Autowired
private ObjectMapper objectMapper;

@MockitoBean
private PedidoServiceImpl pedidoserviceimpl;
private List<Pedido> pedidosLista;

    @Test
    public void verPedidosTest() throws Exception{
        when(pedidoserviceimpl.findByAll()).thenReturn(pedidosLista);
        mockmvc.perform(get("/api/pedidos")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());
    }

    @Test
    public void verunPedidoTest(){
        Pedido unPedido = new Pedido(1L,LocalDate.of(2025,6,9),"entregado", "calle falsa 123");
        try{
            when(pedidoserviceimpl.findById(1L)).thenReturn(Optional.of(unPedido));
            mockmvc.perform(get("/api/pedidos/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        }
        catch(Exception ex){
            fail("El testing lanzo un error"+ex.getMessage());
        }
    }

    @Test
    public void pedidoNoExisteTest() throws Exception{
        when(pedidoserviceimpl.findById(10L)).thenReturn(Optional.empty());
        mockmvc.perform(get("/api/pedidos/10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void crearPedidoTest() throws Exception{
        Pedido unPedido = new Pedido (2L,LocalDate.of(2025,6,9),"procesando pago", "calle dos 9534");
        Pedido otroPedido = new Pedido (3L,LocalDate.of(2025,6,9),"en bodega", "av siempre viva 77105");
        when(pedidoserviceimpl.save(any(Pedido.class))).thenReturn(otroPedido);
        mockmvc.perform(post("/api/pedidos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(unPedido)))
                .andExpect(status().isCreated());
    }
}
