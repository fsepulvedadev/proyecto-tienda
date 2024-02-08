package com.tienda.productos.controller;

import com.tienda.productos.model.Producto;
import com.tienda.productos.service.ProductoService;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")

public class ProductoController {

    @Autowired
    ProductoService productoService;

    @Value("${server.port}")
    private int serverPort;


    @GetMapping("/")
    public ResponseEntity<List<Producto>> getAll () {
        return new ResponseEntity<>(productoService.getAll(), HttpStatus.OK);
    }

    @GetMapping("/traer/{id}")
    public ResponseEntity<Producto> getOne (@PathVariable Long id) {

        System.out.println("------------- Estoy en el puerto: " + serverPort);


        Producto pro = productoService.getById(id);
         if (pro == null) {
             return new ResponseEntity<>(pro, HttpStatus.NOT_FOUND);
         }

         return new ResponseEntity<>(pro, HttpStatus.OK);
    }

    @PostMapping("/guardar")
    public ResponseEntity<Producto> save (@RequestBody Producto pro) {
        return new ResponseEntity<>(productoService.guardar(pro), HttpStatus.OK);
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<String>  borrar (@PathVariable Long id)   {

        String res = productoService.delete(id);

      if (res == null) {
          return new ResponseEntity<>( "No se ha encontrado un producto con ese id.", HttpStatus.NOT_FOUND);
      }
            return new ResponseEntity<>("El producto con id: " + id + " ha sido eliminado.", HttpStatus.OK);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Producto> editar (@PathVariable Long id, @RequestBody Producto pro) {

       Producto prodEditado = productoService.edit(id, pro);

       if (prodEditado == null) {
           return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
       }

       return new ResponseEntity<>(prodEditado, HttpStatus.OK);
    }
}
