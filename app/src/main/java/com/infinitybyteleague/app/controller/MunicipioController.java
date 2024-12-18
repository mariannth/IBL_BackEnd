package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.model.Municipio;
import com.infinitybyteleague.app.service.MunicipioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipios")
public class MunicipioController {

    @Autowired
    private MunicipioService municipioService;

    @GetMapping
    public List<Municipio> listarMunicipios() {
        return municipioService.listarMunicipios();
    }

    @PostMapping
    public Municipio guardarMunicipio(@RequestBody Municipio municipio) {
        return municipioService.guardarMunicipio(municipio);
    }

    @GetMapping("/{id}")
    public Municipio obtenerMunicipioPorId(@PathVariable Integer id) {
        return municipioService.obtenerMunicipioPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarMunicipio(@PathVariable Integer id) {
        municipioService.eliminarMunicipio(id);
    }
}
