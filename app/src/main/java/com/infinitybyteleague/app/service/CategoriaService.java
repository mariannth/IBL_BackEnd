package com.infinitybyteleague.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Categoria;
import com.infinitybyteleague.app.repository.CategoriaRepository;


@Service
public class CategoriaService {
	 private final CategoriaRepository categoriaRepository;

	    @Autowired
	    public CategoriaService(CategoriaRepository categoriaRepository) {
	        this.categoriaRepository = categoriaRepository;
	    }

	    public List<Categoria> getAll() {
	        return categoriaRepository.findAll();
	    }

		public Categoria createCategoria(Categoria newCategoria){
			return this.categoriaRepository.save(newCategoria);
		}

		//Busqueda por id
		public Categoria categoriaById(Integer id){
			return this.categoriaRepository
			.findById(id)
			.orElseThrow(() -> new AppNotFoundException(id));
		}
		
		public Categoria findByCat(String nombreCat) {
			return this.categoriaRepository.findByCategoria(nombreCat);
		}

		//eliminar por id
		public void deleteCategoria(Integer id){
			if(this.categoriaRepository.existsById(id)){
				this.categoriaRepository.deleteById(id);
			}else{
				throw new AppNotFoundException(id);
			}
		}

		//Recuperar por nombre
		public Categoria findByCategoria(String nombreCat) {
			return this.categoriaRepository.findByCategoria(nombreCat);
		}

		//Actualizando
		public Categoria updateCategoria(Categoria categoria, Integer id){
			return this.categoriaRepository.findById(id).map(catField ->{
				catField.setNombreCat(categoria.getNombreCat());
				catField.setDescripcion(categoria.getDescripcion());
				return this.categoriaRepository.save(catField);
			}).orElseThrow(()-> new AppNotFoundException(id));
		}

	   
	}