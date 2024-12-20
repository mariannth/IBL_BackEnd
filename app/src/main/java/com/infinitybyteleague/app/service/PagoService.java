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
    public PagoService(PagoRepository pagoRepository) {
        this.pagoRepository = pagoRepository;
    }

    public List<Pago> getAll() {
        return pagoRepository.findAll();
    }
    
    public Pago createPago(Pago newPago) {
        return this.pagoRepository.save(newPago);
    }
    
    public Pago PagoById(Integer id) {
        return this.pagoRepository
        		.findById(id)
        		.orElseThrow(() -> new AppNotFoundException(id));
    }

    public Pago findByFolioFactura(String folioFactura) {
    	return this.pagoRepository.findByFolioFactura(folioFactura);
    }

    public void deletePago(Integer id) {
        if (this.pagoRepository.existsById(id)) {
            this.pagoRepository.deleteById(id);
        }else {
        	throw new AppNotFoundException(id);
        }
    }
    
    public Pago updatePago(Pago pago, Integer id){
    	return this.pagoRepository.findById(id).map(pagoField ->{
    		pagoField.setDescuento(pago.getDescuento());
    		pagoField.setFecha(pago.getFecha());
    		pagoField.setFolioFactura(pago.getFolioFactura());
    		pagoField.setIva(pago.getIva());
    		pagoField.setTipoDePago(pago.getTipoDePago());
    		return this.pagoRepository.save(pagoField);
    	}).orElseThrow(()-> new AppNotFoundException(id));
               
    }

    
}