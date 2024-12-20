package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Municipio;
import com.infinitybyteleague.app.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/municipios")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class MunicipioController {

    private final MunicipioService municipioService;

    @Autowired
    public MunicipioController(MunicipioService municipioService) {
        this.municipioService = municipioService;
    }

    @GetMapping("/municipios")
    public List<Municipio> getAllMunicipios() {
        return this.municipioService.getAll();
    }
    
    @PostMapping("/new-municipio")
    public ResponseEntity<Municipio> createCategoria(@RequestBody Municipio newMunicipio ){
        if(this.municipioService.findByMun(newMunicipio.getNombreMunicipio()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.municipioService.create(newMunicipio));
    }

  //Mapear el método ById()
    @GetMapping("/mun/{id}")
    public Municipio findById(@PathVariable(name="id") Integer id){
        return this.municipioService.MunById(id);
    }

    //Mapear el metodo deleteById()
    @DeleteMapping("/delete-municipio/{id}")
    public void deleteById(@PathVariable(name="id") Integer id) {
        this.municipioService.deleteMunicipio(id);
    }   

    @PutMapping("/update-municipio/{id}")
    public ResponseEntity<?> updateCategoria(@RequestBody Municipio municipioDetails, @PathVariable (name="id") Integer id ) {
        try {
            return ResponseEntity.ok(this.municipioService.updateMunicipio(municipioDetails, id));
        } catch (AppNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/mun/nombre/{nombreMunicipio}")
    public ResponseEntity<Municipio> findByNombreMunicipio(@PathVariable(name="NombreMunicipio") String NombreMunicipio){
    	if(this.municipioService.findByMun(NombreMunicipio) == null) {
    		return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok(this.municipioService.findByMun(NombreMunicipio));
    }
  
      
}