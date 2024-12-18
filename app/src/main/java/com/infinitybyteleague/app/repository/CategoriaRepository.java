package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Categoria;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
	@Query("SELECT u FROM categoria u WHERE u.nombre = ?1")
	Categoria findByNombreCat(String nombreCat);
}
