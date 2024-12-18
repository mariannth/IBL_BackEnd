package com.infinitybyteleague.app.service;

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
    
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

   	public Producto guardarProducto(Producto producto) {
        return productoRepository.save(producto);
    }

  
    public Producto obtenerProductoPorId(Integer id) {
        return productoRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Producto no encontrado: " + id));
    }

    
    public void eliminarProducto(Integer id) {
        productoRepository.deleteById(id);
    }
}
