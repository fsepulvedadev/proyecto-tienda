package com.tienda.productos.service;

import com.tienda.productos.model.Producto;
import com.tienda.productos.repository.IProductoRepository;
import jakarta.ws.rs.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    IProductoRepository productoRepository;

    @Override
    public Producto guardar(Producto pro) {
        return productoRepository.save(pro);
    }

    @Override
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto getById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    public String delete(Long id)   {

       Optional<Producto> pro = productoRepository.findById(id);

      if (pro.isEmpty()) {
          return null;
      }
       productoRepository.deleteById(id);

      return "El producto ha sido eliminado exitosamente.";
    }

    @Override
    public Producto edit(Long id, Producto pro) {

        Optional<Producto> prodBD = productoRepository.findById(id);

        if(prodBD.isPresent()) {
           Producto nuevoProd = prodBD.get();

           nuevoProd.setCodigo(pro.getCodigo() == null ? nuevoProd.getCodigo() : pro.getCodigo());
           nuevoProd.setNombre(pro.getNombre() == null ? nuevoProd.getNombre() : pro.getNombre());
           nuevoProd.setMarca(pro.getMarca() == null ? nuevoProd.getMarca() : pro.getMarca());
           nuevoProd.setPrecio(pro.getPrecio() == null? nuevoProd.getPrecio() : pro.getPrecio());

            return productoRepository.save(nuevoProd);

        }
        return null;
    }
}
