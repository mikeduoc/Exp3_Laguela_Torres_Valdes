package com.trabajo.spingboot.ecomarket.ecomarket.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "venta")
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String empleado;
    private String sucursal;
    private String producto;
    private int precio;
    private int cantidad;
    private String mediopago;

    public Venta() {
    }

    public Venta(Long id, String empleado, String sucursal, String producto, int precio, int cantidad, String mediopago) {
        this.id = id;
        this.empleado = empleado;
        this.sucursal = sucursal;
        this.producto = producto;
        this.precio = precio;
        this.cantidad = cantidad;
        this.mediopago = mediopago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getMediopago() {
        return mediopago;
    }

    public void setMediopago(String mediopago) {
        this.mediopago = mediopago;
    }
}
