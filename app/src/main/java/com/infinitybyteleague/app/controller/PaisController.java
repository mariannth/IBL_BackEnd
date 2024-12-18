package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.model.Pais;
import com.infinitybyteleague.app.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paises")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping
    public List<Pais> obtenerTodos() {
        return paisService.listarPaises();
    }

    @PostMapping
    public Pais agregar(@RequestBody Pais pais) {
        return paisService.guardarPais(pais);
    }

    @GetMapping("/{id}")
    public Pais obtenerPorId(@PathVariable Integer id) {
        return paisService.obtenerPaisPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        paisService.eliminarPais(id);
    }
}
