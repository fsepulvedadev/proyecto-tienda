package com.tienda.carritos.repository;

import com.tienda.carritos.model.Producto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "productos-service")
public interface ProductosAPIClient {

    @GetMapping("/productos/traer/{product_id}")
     Producto getProducto (@PathVariable Long product_id);
}
