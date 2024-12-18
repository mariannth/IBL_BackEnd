package com.infinitybyteleague.app.service;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Municipio;
import com.infinitybyteleague.app.repository.MunicipioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MunicipioService {
	
    private final MunicipioRepository municipioRepository;

    @Autowired
    private MunicipioService(MunicipioRepository municipioRepository) {
    	this.municipioRepository = municipioRepository;
    }
    
    public List<Municipio> listarMunicipios() {
        return municipioRepository.findAll();
    }

  
    public Municipio guardarMunicipio(Municipio municipio) {
        return municipioRepository.save(municipio);
    }

    
    public Municipio obtenerMunicipioPorId(Integer id) {
        return municipioRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Municipio no encontrado: " + id));
    }

    
    public void eliminarMunicipio(Integer id) {
        municipioRepository.deleteById(id);
    }
    
    public Municipio updateMunicipio(Municipio municipio, Integer id) {
    	return this.municipioRepository.findById(id).map(munField ->{
    		munField.setNombreMunicipio(municipio.getNombreMunicipio());
    		return this.municipioRepository.save(munField);
    	}).orElseThrow(()-> new AppNotFoundException(id));
    }
}
