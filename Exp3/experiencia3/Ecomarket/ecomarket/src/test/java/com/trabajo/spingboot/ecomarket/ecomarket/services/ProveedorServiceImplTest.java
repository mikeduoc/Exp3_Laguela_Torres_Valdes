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

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Proveedor;
import com.trabajo.spingboot.ecomarket.ecomarket.repository.ProveedorRepository;

public class ProveedorServiceImplTest {

    @InjectMocks
    private ProveedorServiceImpl proveedorservice;

    @Mock
    private ProveedorRepository proveedorrepository;

    List<Proveedor> list = new ArrayList<Proveedor>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        this.chargeProveedor();
    }

    @Test
    public void findByAllTest(){

        when(proveedorrepository.findAll()).thenReturn(list);

        List<Proveedor> response = proveedorservice.findByAll();

        assertEquals(3, response.size());

        verify(proveedorrepository,times(1)).findAll();
    }

    public void chargeProveedor(){
        Proveedor provee1 = new Proveedor(Long.valueOf(1),"alan brito","alan.brito@verdurasdeliciosas.cl","+56988451247","camino lonquen km 30");
        Proveedor provee2 = new Proveedor(Long.valueOf(2),"aquiles baeza","aquiles.baeza@condimentosagranel.cl","+56910625400","camino a melipilla 3150");
        Proveedor provee3 = new Proveedor(Long.valueOf(3),"susana oria","susana.oria@frutasorganicas","+56978451122","camino el noviciado 4477");

        list.add(provee1);
        list.add(provee2);
        list.add(provee3);

    }
}
