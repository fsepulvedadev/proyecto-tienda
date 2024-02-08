package com.tienda.carritos.dto;

import com.tienda.carritos.model.Producto;
import lombok.Data;

import java.util.List;

@Data
public class CarritoDTO {

    private Long carrito_id;
    private Double total;
    private List<Producto> listaDeProductos;

}
