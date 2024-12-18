package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.model.Producto;
import com.infinitybyteleague.app.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @GetMapping
    public List<Producto> obtenerTodos() {
        return productoService.listarProductos();
    }

    @PostMapping
    public Producto agregar(@RequestBody Producto producto) {
        return productoService.guardarProducto(producto);
    }

    @GetMapping("/{id}")
    public Producto obtenerPorId(@PathVariable Integer id) {
        return productoService.obtenerProductoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        productoService.eliminarProducto(id);
    }
}
