package com.tienda.ventas.controller;


import com.tienda.ventas.dto.VentaDTO;
import com.tienda.ventas.model.Venta;
import com.tienda.ventas.service.VentasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ventas")
public class VentasController {

    @Autowired
    VentasService ventasService;

    @GetMapping("/")
    public List<VentaDTO> getAll () {
        return ventasService.getAll();
    }

    @GetMapping("/{venta_id}")
    public ResponseEntity<VentaDTO> getById (@PathVariable Long venta_id) {

        VentaDTO respuesta = ventasService.getById(venta_id);

        if( respuesta == null) {
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public VentaDTO save (@RequestBody Venta newVenta) {
       return ventasService.save(newVenta.getFecha(), newVenta.getCarrito_id());
    }

    @DeleteMapping("/borrar/{venta_id}")
    public ResponseEntity<String> delete (@PathVariable Long venta_id) {
       String res = ventasService.delete(venta_id);
        if (res == null) {
            return new ResponseEntity<>("No hay ninguna venta con ese id.", HttpStatus.NOT_FOUND) ;
        }

        return new ResponseEntity<>(ventasService.delete(venta_id), HttpStatus.OK) ;
    }

    @PutMapping("/editar/{venta_id}")
    public ResponseEntity<VentaDTO> update (@PathVariable Long venta_id, @RequestBody Venta newVenta) {

        VentaDTO response = ventasService.update(venta_id, newVenta);

        if (response == null) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
