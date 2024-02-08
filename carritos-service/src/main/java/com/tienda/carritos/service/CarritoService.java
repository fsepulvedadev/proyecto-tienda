package com.tienda.carritos.service;


import com.tienda.carritos.dto.CarritoDTO;
import com.tienda.carritos.model.Carrito;
import com.tienda.carritos.model.Producto;
import com.tienda.carritos.repository.ICarritoRepository;
import com.tienda.carritos.repository.ProductosAPIClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoService implements ICarritoService{

    @Autowired
    ICarritoRepository carritoRepository;

    @Autowired
    ProductosAPIClient productosAPIClient;

    @Override
    public CarritoDTO save(List<Long> listaDeProd) {
        Carrito nuevoCarrito = new Carrito();

        nuevoCarrito.setLista_productos(listaDeProd);
        nuevoCarrito.setTotal_carrito(this.calcularTotalProductos(listaDeProd));

        Carrito nuevoCarr = carritoRepository.save(nuevoCarrito);



         return armarDto(nuevoCarr);
    }

    @Override
    public List<CarritoDTO> getAll() {
        List<Carrito> listaCarrosDB = carritoRepository.findAll();

        List<CarritoDTO> listaCarrosDTO = new ArrayList<>();

        for (Carrito car : listaCarrosDB) {
           listaCarrosDTO.add(this.armarDto(car)) ;
        }

        return listaCarrosDTO;
    }

    @Override
    public CarritoDTO getOne(Long id) {

      Optional<Carrito>  carritoDB = carritoRepository.findById(id);

      if(carritoDB.isPresent()) {
          CarritoDTO carritoRes = new CarritoDTO();

          carritoRes.setCarrito_id(carritoDB.get().getCarrito_id());
          carritoRes.setTotal(carritoDB.get().getTotal_carrito());
          carritoRes.setListaDeProductos(this.armarListaDetallada(carritoDB.get().getLista_productos()));
          return carritoRes;
      }

        return null;
    }

    @Override
    public CarritoDTO edit(Long id, List<Long> listaIds) {

        Optional<Carrito> carrDB = carritoRepository.findById(id);

        if(carrDB.isPresent()) {

            Carrito nuevoCarr = carrDB.get();

            nuevoCarr.setLista_productos(listaIds == null ? nuevoCarr.getLista_productos() : listaIds);

            nuevoCarr.setTotal_carrito(this.calcularTotalProductos(nuevoCarr.getLista_productos()));


            return armarDto(carritoRepository.save(nuevoCarr));

        }
        return null;
    }

    @Override
    public String delete(Long id) {

        Optional<Carrito> car = carritoRepository.findById(id);

        if (car.isEmpty()) {
            return null;
        }
        carritoRepository.deleteById(id);

        return "El carrito ha sido eliminado exitosamente.";
    }

    @Override
    public List<Producto> traerListaProd (Long carrito_id) {
       CarritoDTO carDB = this.getOne(carrito_id);

        if ( carDB == null) {
            return null;
        }

      return carDB.getListaDeProductos();
    }

    private Double calcularTotalProductos (List<Long> listaIds) {

        Double total = 0.0;

        for (Long idProd : listaIds) {
            Producto prod = productosAPIClient.getProducto(idProd);

            if(prod == null)  {
                total += 0;
            }
            total += prod.getPrecio();
        }

        return total;
    }

    private CarritoDTO armarDto (Carrito car){

        CarritoDTO nuevoCarDTO = new CarritoDTO();

        nuevoCarDTO.setCarrito_id(car.getCarrito_id());
        nuevoCarDTO.setTotal(car.getTotal_carrito());
        nuevoCarDTO.setListaDeProductos(this.armarListaDetallada(car.getLista_productos()));

        return  nuevoCarDTO;

    }
    private List<Producto> armarListaDetallada (List<Long> listaDeIds) {
        List<Producto> listaDeProductos = new ArrayList<>();

        for (Long idProd : listaDeIds) {
            Producto prod = productosAPIClient.getProducto(idProd);

            if(prod == null)  {
                Producto noEncontrado =  new Producto();

                noEncontrado.setCodigo("Id no encontrado");
                noEncontrado.setMarca("Id no encontrado");
                noEncontrado.setNombre("Id no encontrado");
                noEncontrado.setProducto_id(idProd);
                noEncontrado.setPrecio(null);

                listaDeProductos.add(noEncontrado);
            }
            listaDeProductos.add(prod);
            }

        return listaDeProductos;
        }
    }



