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

import com.trabajo.spingboot.ecomarket.ecomarket.entities.Cliente;
import com.trabajo.spingboot.ecomarket.ecomarket.repository.ClienteRepository;

public class ClienteServiceImplTest {

    @InjectMocks
    private ClienteServiceImpl clienteservice;

    @Mock
    private ClienteRepository clienterepository;

    List<Cliente> list = new ArrayList<Cliente>();

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        this.chargeCliente();
    }

    @Test
    public void findByAllTest(){

        when(clienterepository.findAll()).thenReturn(list);

        List<Cliente> response = clienteservice.findByAll();

        assertEquals(3, response.size());

        verify(clienterepository,times(1)).findAll();
    }

    public void chargeCliente(){
        Cliente clie1 = new Cliente(Long.valueOf(1),"bin","bin.laden@gmail.com", "calle falsa 123");
        Cliente clie2 = new Cliente(Long.valueOf(2),"ignacio","dos.silas@duocuc.cl", "calle dos 9534");
        Cliente clie3 = new Cliente(Long.valueOf(3),"matias","matii.torres@duocuc.cl", "av siempre viva 77105");

        list.add(clie1);
        list.add(clie2);
        list.add(clie3);

    }
}
