package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Producto;
import com.infinitybyteleague.app.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class ProductoController {

    private final ProductoService productoService;

    @Autowired
    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    // Mapear el método getAll()
    @GetMapping("/producto")
    public List<Producto> getAllCategorias() {
        return this.productoService.getAll();
    }

    //Método Post manejando Status HTTP
    @PostMapping("/new-producto")
    public ResponseEntity<Producto> createCategoria(@RequestBody Producto newProducto ){
        if(this.productoService.findByProducto(newProducto.getNombreProducto()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.productoService.createProducto(newProducto));
    }

    //Mapear el método ById()
    @GetMapping("/producto/{id}")
    public Producto findById(@PathVariable(name="id") Integer id){
        return this.productoService.ProductoById(id);
    }

    //Mapear el metodo deleteById()
    @DeleteMapping("/delete-producto/{id}")
    public void deleteById(@PathVariable(name="id") Integer id) {
        this.productoService.delete(id);
    }   

    @PutMapping("/update-producto/{id}")
    public ResponseEntity<?> updateProducto(@RequestBody Producto productoDetails, @PathVariable (name="id") Integer id ) {
        try {
            return ResponseEntity.ok(this.productoService.updateProducto(productoDetails, id));
        } catch (AppNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
 // Buscar producto por nombre
    @GetMapping("/producto/nombre/{nombreProducto}")
    public ResponseEntity<Producto> findByProducto(@PathVariable(name = "nombreProducto") String nombreProducto) {
        Producto producto = this.productoService.findByProducto(nombreProducto);
        if (producto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);
    }
  
      
}