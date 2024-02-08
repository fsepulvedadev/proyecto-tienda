package com.tienda.ventas.service;

import com.tienda.ventas.dto.VentaDTO;
import com.tienda.ventas.model.Carrito;
import com.tienda.ventas.model.Venta;
import com.tienda.ventas.repository.CarritosApiClient;
import com.tienda.ventas.repository.IVentasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class VentasService implements IVentasService {

    @Autowired
    IVentasRepository ventasRepository;

    @Autowired
    CarritosApiClient carritosApiClient;

    @Override
    public VentaDTO save(LocalDate fecha, Long carrito_id) {

        Venta nueva = new Venta();

        nueva.setFecha(fecha);
        nueva.setCarrito_id(carrito_id);

       return armarDto(ventasRepository.save(nueva));

    }

    @Override
    public List<VentaDTO> getAll() {
     List<Venta> listaVentas = ventasRepository.findAll();
     List<VentaDTO> listaDtos = new ArrayList<>();

     for(Venta ven : listaVentas) {
         listaDtos.add(armarDto(ven));
     }

     return listaDtos;
    }

    @Override
    public VentaDTO getById(Long venta_id) {
        return armarDto(ventasRepository.findById(venta_id).orElse(null));
    }

    @Override
    public String delete(Long venta_id) {

        VentaDTO res = this.getById(venta_id);

        if (res == null) {
            return null;
        }

         ventasRepository.deleteById(venta_id);

        return "La venta ha sido eliminada de la base de datos.";
    }

    @Override
    public VentaDTO update(Long carrito_id, Venta newVenta) {

    Optional<Venta> ventaDb = ventasRepository.findById(carrito_id);

    if( ventaDb.isPresent()) {
      Venta ventaActualizada =  ventaDb.get();

      ventaActualizada.setCarrito_id(newVenta.getCarrito_id() == null ? ventaActualizada.getCarrito_id() : newVenta.getCarrito_id());
      ventaActualizada.setFecha(newVenta.getFecha() == null ? ventaActualizada.getFecha() : newVenta.getFecha());

      return armarDto(ventasRepository.save(ventaActualizada)) ;
    }
        return null;
    }

    VentaDTO armarDto (Venta venta) {

        if (venta == null) {
            return null;
        }
        VentaDTO nuevoDto = new VentaDTO();


        nuevoDto.setVenta_id(venta.getVenta_id());
        nuevoDto.setFecha(venta.getFecha());
        nuevoDto.setCarrito(carritosApiClient.getCarritoById(venta.getCarrito_id()));

        return nuevoDto;
    }
}
