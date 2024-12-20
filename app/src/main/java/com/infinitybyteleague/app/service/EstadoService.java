package com.infinitybyteleague.app.service;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Estado;
import com.infinitybyteleague.app.repository.EstadoRepository;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {
    private final EstadoRepository estadoRepository;

    @Autowired
    public EstadoService(EstadoRepository estadoRepository) {
        this.estadoRepository = estadoRepository;
    }

    public List<Estado> getAll() {
        return estadoRepository.findAll();
    }
    
    public Estado createEstado(Estado estado) {
        return this.estadoRepository.save(estado);
    }

    public Estado estadoById(Integer id) {
        return this.estadoRepository
        		.findById(id)
        		.orElseThrow(() -> new AppNotFoundException(id));
    }

    public Estado findByEstado(String NombreEstado) {
    	return this.estadoRepository.findByEstado(NombreEstado);
    }
    
    public void deleteEstado(Integer id) {
        if (this.estadoRepository.existsById(id)) {
            this.estadoRepository.deleteById(id);
        }else {
        	throw new AppNotFoundException(id);
        }
    }

    public Estado updateEstado(Estado estado, Integer id){
        return this.estadoRepository.findById(id).map(estadoField ->{
        	estadoField.setNombreEstado(estado.getNombreEstado());
            return this.estadoRepository.save(estadoField);
        }).orElseThrow(()-> new AppNotFoundException(id));
                
    }

    
}