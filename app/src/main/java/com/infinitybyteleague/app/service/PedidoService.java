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

    public List<Pedido> getAll() {
        return pedidoRepository.findAll();
    }
    
    public Pedido createPedido(Pedido newPedido) {
        return this.pedidoRepository.save(newPedido);
    }

    public Pedido PedidoById(Integer id) {
        return this.pedidoRepository
        		.findById(id)
        		.orElseThrow(()-> new AppNotFoundException(id));
    }
    
    public List<Pedido> findByEstPed(String estadoPedido) {
    	return this.pedidoRepository.findByEstadoPedido(estadoPedido);
    }
    
    public void deletePedido(Integer id) {
        if (this.pedidoRepository.existsById(id)) {
            this.pedidoRepository.deleteById(id);
        }else {
        	throw new AppNotFoundException(id);
        }
    }
    
    public Pedido updatePedido(Pedido pedido, Integer id){
        return this.pedidoRepository.findById(id).map(pedidoField->{
        	pedidoField.setFecha(pedido.getFecha());
        	pedidoField.setEstadoDePedido(pedido.getEstadoDePedido());
        	return this.pedidoRepository.save(pedidoField);
        }).orElseThrow(()-> new AppNotFoundException(id));       
    }

    
}
