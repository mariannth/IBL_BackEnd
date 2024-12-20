package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Estado;
import com.infinitybyteleague.app.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class EstadoController {

    private final EstadoService estadoService;

    @Autowired
    public EstadoController(EstadoService estadoService) {
        this.estadoService = estadoService;
    }

    @GetMapping("/estados")
    public List<Estado> getAllEstados() {
        return this.estadoService.getAll();
    }
    
    @PostMapping("/new-estado")
    public ResponseEntity<Estado> createEstado(@RequestBody Estado newEstado) {
        if(this.estadoService.findByEstado(newEstado.getNombreEstado()) != null) {
        	return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.estadoService.createEstado(newEstado));
    }

    @GetMapping("/estados/{id}")
    public Estado EstadoById(@PathVariable(name="id") Integer id) {
        return this.estadoService.estadoById(id);
       }

    @DeleteMapping("/delete-estados/{id}")
    public void deleteEstado(@PathVariable(name="id") Integer id) {
    	this.estadoService.deleteEstado(id);
    }

    @PutMapping("/update-estados/{id}")
    public ResponseEntity<?> updateEstado(@PathVariable(name="id") Integer id, @RequestBody Estado estadoDetails) {
        try {
            return ResponseEntity.ok(estadoService.updateEstado(estadoDetails, id));
        } catch (AppNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/edo/nombre/{nombreEstado}")
    public ResponseEntity<Estado> EstadoById(@PathVariable(name="nombreEstado") String NombreEstado){
    	if(this.estadoService.findByEstado(NombreEstado)== null) {
    		return ResponseEntity.notFound().build();
    	}
    	return ResponseEntity.ok(this.estadoService.findByEstado(NombreEstado));
    }

    
}