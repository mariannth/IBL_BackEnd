package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Categoria;
import com.infinitybyteleague.app.service.CategoriaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class CategoriaController {
    private final CategoriaService categoriaService;

    @Autowired
    public CategoriaController(CategoriaService categoriaService) {
        this.categoriaService = categoriaService;
    }
    // Mapear el método getAll()
    @GetMapping("/categorias")
    public List<Categoria> getAllCategorias() {
        return this.categoriaService.getAll();
    }

    //Método Post manejando Status HTTP
    @PostMapping("/new-categoria")
    public ResponseEntity<Categoria> createCategoria(@RequestBody Categoria newCategoria ){
        if(this.categoriaService.findByCategoria(newCategoria.getNombreCat()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.categoriaService.createCategoria(newCategoria));
    }

    //Mapear el método ById()
    @GetMapping("/cat/{id}")
    public Categoria findById(@PathVariable(name="id") Integer id){
        return this.categoriaService.categoriaById(id);
    }

    //Mapear el metodo deleteById()
    @DeleteMapping("/delete-categoria/{id}")
    public void deleteById(@PathVariable(name="id") Integer id) {
        this.categoriaService.deleteCategoria(id);
    }   

    @PutMapping("/update-categoria/{id}")
    public ResponseEntity<?> updateCategoria(@RequestBody Categoria categoriaDetails, @PathVariable (name="id") Integer id ) {
        try {
            return ResponseEntity.ok(this.categoriaService.updateCategoria(categoriaDetails, id));
        } catch (AppNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/cat/nombre/{nombreCat}")
    public ResponseEntity<Categoria> findByCat(@PathVariable(name="nombreCat") String nombreCat){
    	if(this.categoriaService.findByCategoria(nombreCat) == null) {
    		return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok(this.categoriaService.findByCategoria(nombreCat));
    }
  
      
}