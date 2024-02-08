package com.tienda.ventas.model;

import lombok.Data;

@Data
public class Producto {

    private Long producto_id;

    private String codigo;
    private String nombre;
    private String marca;
    private Double precio;
}
