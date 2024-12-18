package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.model.Estado;
import com.infinitybyteleague.app.service.EstadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estados")
public class EstadoController {

    @Autowired
    private EstadoService estadoService;

    @GetMapping
    public List<Estado> obtenerTodos() {
        return estadoService.listarEstados();
    }

    @PostMapping
    public Estado agregar(@RequestBody Estado estado) {
        return estadoService.guardarEstado(estado);
    }

    @GetMapping("/{id}")
    public Estado obtenerPorId(@PathVariable Integer id) {
        return estadoService.obtenerEstadoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        estadoService.eliminarEstado(id);
    }
}
