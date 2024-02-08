package com.tienda.ventas.model;

import lombok.Data;

import java.util.List;

@Data
public class Carrito {

    private Long carrito_id;
    private Double total;
    private List<Producto> listaDeProductos;

}
