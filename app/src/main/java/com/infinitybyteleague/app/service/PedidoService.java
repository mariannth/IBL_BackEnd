package com.infinitybyteleague.app.service;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Pedido;
import com.infinitybyteleague.app.repository.PedidoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {
	
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
		this.pedidoRepository = pedidoRepository;
	}


	public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    
    public Pedido guardarPedido(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    
    public Pedido obtenerPedidoPorId(Integer id) {
        return pedidoRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Pedido no encontrado: " + id));
    }

    
    public void eliminarPedido(Integer id) {
        pedidoRepository.deleteById(id);
    }
    
    public Pedido updatePedido(Pedido pedido, Integer id) {
    	return this.pedidoRepository.findById(id).map(pedidoField ->{
    		pedidoField.setEstadoDePedido(pedido.getEstadoDePedido());
    		pedidoField.setFecha(pedido.getFecha());
    		pedidoField.setTotal(pedido.getTotal());
    		return this.pedidoRepository.save(pedidoField);
    	}).orElseThrow(() -> new AppNotFoundException(id));
    }
}
