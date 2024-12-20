package com.infinitybyteleague.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.infinitybyteleague.app.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    // Buscar categoría por nombre
	@Query("SELECT c FROM Categoria c WHERE c.nombreCat = ?1")
	Categoria findByCategoria(String nombreCat);

}
