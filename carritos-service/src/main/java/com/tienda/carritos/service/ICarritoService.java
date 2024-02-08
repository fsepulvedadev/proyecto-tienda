package com.tienda.carritos.service;

import com.tienda.carritos.dto.CarritoDTO;
import com.tienda.carritos.model.Carrito;
import com.tienda.carritos.model.Producto;

import java.util.List;

public interface ICarritoService {

    CarritoDTO save(List<Long> car);

    List<CarritoDTO> getAll();

    CarritoDTO getOne(Long id);

    CarritoDTO edit(Long id , List<Long> listaDeIds);

    String delete(Long id);

    List<Producto> traerListaProd(Long carrito_id);
}
