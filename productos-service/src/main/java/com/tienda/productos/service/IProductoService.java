package com.tienda.productos.service;


import com.tienda.productos.model.Producto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductoService {

     Producto guardar (Producto pro);

     List<Producto> getAll();

     Producto getById(Long id);

     String delete(Long id);

     Producto edit(Long id, Producto pro);
}
