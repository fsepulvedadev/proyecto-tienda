package com.tienda.ventas.service;

import com.tienda.ventas.dto.VentaDTO;
import com.tienda.ventas.model.Venta;

import java.time.LocalDate;
import java.util.List;

public interface IVentasService {

    VentaDTO save(LocalDate fecha, Long carrito_id);

    List<VentaDTO> getAll ();

    VentaDTO getById(Long carrito_id);

    String delete (Long carrito_id);

    VentaDTO update(Long carrito_id, Venta newVenta);
}
