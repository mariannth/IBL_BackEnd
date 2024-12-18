package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.model.Pago;
import com.infinitybyteleague.app.service.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @GetMapping
    public List<Pago> listarPagos() {
        return pagoService.listarPagos();
    }

    @PostMapping
    public Pago guardarPago(@RequestBody Pago pago) {
        return pagoService.guardarPago(pago);
    }

    @GetMapping("/{id}")
    public Pago obtenerPagoPorId(@PathVariable Integer id) {
        return pagoService.obtenerPagoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarPago(@PathVariable Integer id) {
        pagoService.eliminarPago(id);
    }
}

