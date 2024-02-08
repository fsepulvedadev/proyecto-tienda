package com.tienda.carritos.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Carrito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long carrito_id;

    private Double total_carrito;


    @ElementCollection
    private List<Long> lista_productos;
}
