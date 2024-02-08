package com.tienda.ventas.repository;

import com.tienda.ventas.model.Carrito;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "carritos-service" )
public interface CarritosApiClient {

    @GetMapping("/carritos/{carrito_id}")
    Carrito getCarritoById (@PathVariable Long carrito_id);
}
