package com.tienda.ventas.dto;

import com.tienda.ventas.model.Carrito;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VentaDTO {

    private Long venta_id;
    private LocalDate fecha;

    private Carrito carrito;

}
