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
    private EstadoService(EstadoRepository estadoRepository) {
    	this.estadoRepository=estadoRepository;
    }

    //Método para obtener todos los estados 
    public List<Estado> listarEstados() {
        return estadoRepository.findAll();
    }

   //Método para crear un nuevo estado
    public Estado guardarEstado(Estado newestado) {
        return this.estadoRepository.save(newestado);
    }

    //Buscar por id
    public Estado obtenerEstadoPorId(Integer id) {
        return estadoRepository
        		.findById(id)
        		.orElseThrow(() -> new RuntimeException("Estado no encontrado: " + id));
    }

  //Método para recuperar por nombre
    public Estado findByEstado(String nombreEstado) {
 	   return this.estadoRepository.findByEstado(nombreEstado);
    }
    
    //Método para eliminar categoria
    public void eliminarEstado(Integer id) {
        estadoRepository.deleteById(id);
    }
  //Método para actualizar la categoría
    public Estado updateEstado(Estado estado, Integer id) {
 	   return this.estadoRepository.findById(id).map(estadoField ->{
 		   estadoField.setNombreEstado(estado.getNombreEstado());
 		   return this.estadoRepository.save(estadoField);
 	   }).orElseThrow(() -> new AppNotFoundException(id));
    }
    
}
