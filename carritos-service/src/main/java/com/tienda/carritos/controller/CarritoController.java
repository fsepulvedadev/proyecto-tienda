package com.tienda.carritos.controller;

import com.tienda.carritos.dto.CarritoDTO;
import com.tienda.carritos.model.Carrito;
import com.tienda.carritos.model.Producto;
import com.tienda.carritos.service.CarritoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carritos")
public class CarritoController {

    @Autowired
    CarritoService carritoService;

    @GetMapping("/")
    List<CarritoDTO> getAll() {
        return carritoService.getAll();
    }

    @GetMapping("/{carrito_id}")
    ResponseEntity<CarritoDTO> getOne(@PathVariable Long carrito_id) {
       CarritoDTO car = carritoService.getOne(carrito_id);

       if(car == null) {
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
       }

       return new ResponseEntity<>(car, HttpStatus.OK);
    }

    @GetMapping("/productos/{carrito_id}")
    ResponseEntity<List<Producto>> traerListaProd(@PathVariable Long carrito_id) {
        List<Producto> listaProd = carritoService.traerListaProd(carrito_id);

        if(listaProd == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(listaProd, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    ResponseEntity<CarritoDTO> save(@RequestBody Carrito car) {
       return new ResponseEntity<>(carritoService.save(car.getLista_productos()), HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    ResponseEntity<CarritoDTO> edit (@PathVariable Long id, @RequestBody Carrito car) {
       CarritoDTO carr = carritoService.edit(id, car.getLista_productos());

       if(car == null) {
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
       }
       return new ResponseEntity<>(carr,HttpStatus.OK);
    }
}
