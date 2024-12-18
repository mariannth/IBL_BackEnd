package com.infinitybyteleague.app.service;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Pago;
import com.infinitybyteleague.app.repository.PagoRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoService {
	
    private final PagoRepository pagoRepository;
    
    @Autowired
    private PagoService(PagoRepository pagoRepository) {
    	this.pagoRepository=pagoRepository;
    }
    
    public List<Pago> listarPagos() {
        return pagoRepository.findAll();
    }

   
    public Pago guardarPago(Pago pago) {
        return pagoRepository.save(pago);
    }

    
    public Pago obtenerPagoPorId(Integer id) {
        return pagoRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Pago no encontrado: " + id));
    }

    
    public void eliminarPago(Integer id) {
        pagoRepository.deleteById(id);
    }
    
    public Pago updatePago(Pago pago, Integer id) {
    	return this.pagoRepository.findById(id).map(pagoField->{
    		pagoField.setDescuento(pago.getDescuento());
    		pagoField.setFecha(pago.getFecha());
    		pagoField.setFolioFactura(pago.getFolioFactura());
    		pagoField.setIva(pago.getIva());
    		pagoField.setTipoDePago(pago.getTipoDePago());
    		return this.pagoRepository.save(pagoField);
    	}).orElseThrow(()-> new AppNotFoundException(id));
    }
    
}