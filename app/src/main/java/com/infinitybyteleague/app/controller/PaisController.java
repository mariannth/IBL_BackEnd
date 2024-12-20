package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Pais;
import com.infinitybyteleague.app.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PaisController {

    private final PaisService paisService;

    @Autowired
    public PaisController(PaisService paisService) {
        this.paisService = paisService;
    }
    // Mapear el método getAll()
    @GetMapping("/paises")
    public List<Pais> getAllPaises() {
        return this.paisService.getAll();
    }

    //Método Post manejando Status HTTP
    @PostMapping("/new-paises")
    public ResponseEntity<Pais> createPaises(@RequestBody Pais newPais ){
        if(this.paisService.findByPais(newPais.getNombrePais()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.paisService.createPais(newPais));
    }

    //Mapear el método ById()
    @GetMapping("/pais/{id}")
    public Pais findById(@PathVariable(name="id") Integer id){
        return this.paisService.PaisById(id);
    }

    //Mapear el metodo deleteById()
    @DeleteMapping("/delete-paises/{id}")
    public void deleteById(@PathVariable(name="id") Integer id) {
        this.paisService.deletePais(id);
    }   

    @PutMapping("/update-paises/{id}")
    public ResponseEntity<?> updatePaises(@RequestBody Pais paisDetails, @PathVariable (name="id") Integer id ) {
        try {
            return ResponseEntity.ok(this.paisService.updatePais(paisDetails, id));
        } catch (AppNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    // Buscar país por nombre
    @GetMapping("/pais/nombre/{nombrePais}")
    public ResponseEntity<Pais> findByNombrePais(@PathVariable(name = "nombrePais") String nombrePais) {
        Pais pais = this.paisService.findByPais(nombrePais);
        if (pais == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pais);
    }
  
      
}