package com.trabajo.spingboot.ecomarket.ecomarket.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Pedido;
import com.trabajo.spingboot.ecomarket.ecomarket.repository.PedidoRepository;

public class PedidoServiceImplTest {

    @InjectMocks
    private PedidoServiceImpl pedidoservice;

    @Mock
    private PedidoRepository pedidorepository;

    List<Pedido> list = new ArrayList<Pedido>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        this.chargePedido();
    }

    @Test
    public void findByAllTest(){

        when(pedidorepository.findAll()).thenReturn(list);

        List<Pedido> response = pedidoservice.findByAll();

        assertEquals(3, response.size());

        verify(pedidorepository,times(1)).findAll();
    }

    public void chargePedido(){
        Pedido ped1 = new Pedido(Long.valueOf(1),LocalDate.of(2025,6,9),"entregado", "calle falsa 123");
        Pedido ped2 = new Pedido(Long.valueOf(2),LocalDate.of(2025,6,9),"procesando pago", "calle dos 9534");
        Pedido ped3 = new Pedido(Long.valueOf(3),LocalDate.of(2025,6,9),"en bodega", "av siempre viva 77105");

        list.add(ped1);
        list.add(ped2);
        list.add(ped3);

    }
}
