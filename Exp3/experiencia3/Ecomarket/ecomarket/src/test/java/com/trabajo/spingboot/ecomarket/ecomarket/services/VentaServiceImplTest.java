package com.trabajo.spingboot.ecomarket.ecomarket.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Venta;
import com.trabajo.spingboot.ecomarket.ecomarket.repository.VentaRepository;

public class VentaServiceImplTest {
    @InjectMocks
    private VentaServiceImpl ventaservice;

    @Mock
    private VentaRepository ventarepository;

    List<Venta> list = new ArrayList<Venta>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        this.chargeVenta();
    }

    @Test
    public void findByAllTest(){

        when(ventarepository.findAll()).thenReturn(list);

        List<Venta> response = ventaservice.findByAll();

        assertEquals(3, response.size());

        verify(ventarepository,times(1)).findAll();
    }

    public void chargeVenta(){
        Venta vent1 = new Venta(Long.valueOf(1),"elba lazo","santiago","lechuga hidroponica",1500,1,"debito");
        Venta vent2 = new Venta(Long.valueOf(2),"aquiles bailo","antofagasta","sal de mar",3500,2,"credito");
        Venta vent3 = new Venta(Long.valueOf(3),"lola mento","temuco","oregano entero",2500,3,"efectivo");

        list.add(vent1);
        list.add(vent2);
        list.add(vent3);

    }
}
