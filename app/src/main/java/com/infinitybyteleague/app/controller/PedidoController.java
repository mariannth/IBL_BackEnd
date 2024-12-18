package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.model.Pedido;
import com.infinitybyteleague.app.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public List<Pedido> obtenerTodos() {
        return pedidoService.listarPedidos();
    }

    @PostMapping
    public Pedido agregar(@RequestBody Pedido pedido) {
        return pedidoService.guardarPedido(pedido);
    }

    @GetMapping("/{id}")
    public Pedido obtenerPorId(@PathVariable Integer id) {
        return pedidoService.obtenerPedidoPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        pedidoService.eliminarPedido(id);
    }
}
