package com.infinitybyteleague.app.service;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Producto;
import com.infinitybyteleague.app.repository.ProductoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductoService {
    private final ProductoRepository productoRepository;

    @Autowired
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> getAll() {
        return productoRepository.findAll();
    }
    
    public Producto createProducto(Producto newProducto) {
        return this.productoRepository.save(newProducto);
    }

    public Producto ProductoById(Integer id) {
        return this.productoRepository
        		.findById(id)
        		.orElseThrow(() -> new AppNotFoundException(id));
    }
   
    public Producto findByProducto(String nombreProducto) {
    	return this.productoRepository.findByNombreProducto(nombreProducto);
    }
    
    public void delete(Integer id) {
        if (this.productoRepository.existsById(id)) {
            this.productoRepository.deleteById(id);
        }else{
        	throw new AppNotFoundException(id);
        }
    }
    
    public Producto updateProducto(Producto producto, Integer id){
       return this.productoRepository.findById(id).map(productoField -> {
    	   productoField.setNombreProducto(producto.getNombreProducto());
           productoField.setDescripcion(producto.getDescripcion());
           return this.productoRepository.save(producto);
       }).orElseThrow(() -> new AppNotFoundException(id));
    }   
}