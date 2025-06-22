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

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Producto;
import com.trabajo.spingboot.ecomarket.ecomarket.repository.ProductoRepository;

public class ProductServiceImplTest {

    @InjectMocks
    private ProductoServiceImpl productoservice;

    @Mock
    private ProductoRepository productorepository;

    List<Producto> list = new ArrayList<Producto>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        this.chargeProducto();
    }

    @Test
    public void findByAllTest(){

        when(productorepository.findAll()).thenReturn(list);

        List<Producto> response = productoservice.findByAll();

        assertEquals(3, response.size());

        verify(productorepository,times(1)).findAll();
    }

    public void chargeProducto(){
        Producto prod1 = new Producto(Long.valueOf(1),"lechuga","organica", 1500,10);
        Producto prod2 = new Producto(Long.valueOf(2),"sal","de mar", 3500,5);
        Producto prod3 = new Producto(Long.valueOf(3),"oregano","entero", 2500,15);

        list.add(prod1);
        list.add(prod2);
        list.add(prod3);

    }
}
