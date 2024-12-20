package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Pedido;
import com.infinitybyteleague.app.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class PedidoController {

    private final PedidoService pedidoService;

    @Autowired
    public PedidoController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }
    // Mapear el método getAll()
    @GetMapping("/pedidos")
    public List<Pedido> getAllCategorias() {
        return this.pedidoService.getAll();
    }

    //Método Post manejando Status HTTP
    @PostMapping("/new-pedidos")
    public ResponseEntity<Pedido> createCategoria(@RequestBody Pedido newPedido ){
        if(this.pedidoService.findByEstPed(newPedido.getEstadoDePedido()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.pedidoService.createPedido(newPedido));
    }

    //Mapear el método ById()
    @GetMapping("/pedido/{id}")
    public Pedido findById(@PathVariable(name="id") Integer id){
        return this.pedidoService.PedidoById(id);
    }

    //Mapear el metodo deleteById()
    @DeleteMapping("/delete-pedidos/{id}")
    public void deleteById(@PathVariable(name="id") Integer id) {
        this.pedidoService.deletePedido(id);
    }   

    @PutMapping("/update-pedidos/{id}")
    public ResponseEntity<?> updateCategoria(@RequestBody Pedido pedidoDetails, @PathVariable (name="id") Integer id ) {
        try {
            return ResponseEntity.ok(this.pedidoService.updatePedido(pedidoDetails, id));
        } catch (AppNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
 // Buscar pedidos por estado
    @GetMapping("/pedido/estado/{estadoPedido}")
    public ResponseEntity<List<Pedido>> findByEstadoPedido(@PathVariable(name = "estadoPedido") String estadoPedido) {
        List<Pedido> pedidos = this.pedidoService.findByEstPed(estadoPedido);
        if (pedidos == null || pedidos.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(pedidos);
    }
  
      
}