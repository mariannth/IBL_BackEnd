package com.infinitybyteleague.app.service;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Pais;
import com.infinitybyteleague.app.repository.PaisRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisService {
	
    private final PaisRepository paisRepository;

    @Autowired
    public PaisService(PaisRepository paisRepository) {
  		this.paisRepository = paisRepository;
  	}
    
    public List<Pais> listarPaises() {
        return paisRepository.findAll();
    }

    
 	public Pais guardarPais(Pais pais) {
        return paisRepository.save(pais);
    }

   
    public Pais obtenerPaisPorId(Integer id) {
        return paisRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("País no encontrado: " + id));
    }

    
    public void eliminarPais(Integer id) {
        paisRepository.deleteById(id);
    }
    
    public Pais updatePais(Pais pais, Integer id) {
		return this.paisRepository.findById(id).map(paisField ->{
			paisField.setNombrePais(pais.getNombrePais());
			return this.paisRepository.save(paisField);
		}).orElseThrow(()->new AppNotFoundException(id));
    	
    }
}