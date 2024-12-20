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

    public List<Pais> getAll() {
        return paisRepository.findAll();
    }
    
    public Pais createPais(Pais newPais) {
        return this.paisRepository.save(newPais);
    }
    
    public Pais PaisById(Integer id) {
        return this.paisRepository
        		.findById(id)
        		.orElseThrow(()-> new AppNotFoundException(id));
    }

   public Pais findByPais(String NombrePais) {
	   return this.paisRepository.findByNombrePais(NombrePais);
   }

   public void deletePais(Integer id) {
       if (this.paisRepository.existsById(id)) {
           this.paisRepository.deleteById(id);
       }else {
    	throw new AppNotFoundException(id);   
       }
   }
   
    public Pais updatePais(Pais pais, Integer id) {
        return this.paisRepository.findById(id).map(paisField ->{
        	paisField.setNombrePais(pais.getNombrePais());
        	return this.paisRepository.save(paisField);
        }).orElseThrow(()-> new AppNotFoundException(id));
    }

    
}