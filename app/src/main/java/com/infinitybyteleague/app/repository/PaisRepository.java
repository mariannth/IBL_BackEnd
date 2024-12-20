package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
     // Buscar país por nombre
	    @Query("SELECT p FROM Pais p WHERE p.nombrePais = ?1")
	    Pais findByNombrePais(String nombrePais);
}