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
   public CategoriaService(CategoriaRepository categoriaRepository){
	   this.categoriaRepository= categoriaRepository;
   }
   
   //Método para obtener todas las categorias
   public List<Categoria> listarCategorias() {
       return categoriaRepository.findAll();
   }
   
   //Método para crear una nueva categoría
   public Categoria guardarCategoria(Categoria newcategoria) {
       return this.categoriaRepository.save(newcategoria);
   }
   
   //Busqueda por id
   public Categoria obtenerCategoriaPorId(Integer id) {
       return categoriaRepository
    		   .findById(id)
    		   .orElseThrow(() -> new RuntimeException("Categoría no encontrada: " + id));
   }
   
   //Método para recuperar por nombre
   public Categoria findByNombreCat(String nombreCat) {
	   return this.categoriaRepository.findByNombreCat(nombreCat);
   }
   
   //Método para eliminar categoria
   public void eliminarCategoria(Integer id) {
       categoriaRepository.deleteById(id);
   }
   
   //Método para actualizar la categoría
   public Categoria updateCategoria(Categoria categoria, Integer id) {
	   return this.categoriaRepository.findById(id).map(catField ->{
		   catField.setNombre(categoria.getNombre());
		   catField.setDescripcion(categoria.getDescripcion());
		   return this.categoriaRepository.save(catField);
	   }).orElseThrow(() -> new AppNotFoundException(id));
   }
   
   
}
