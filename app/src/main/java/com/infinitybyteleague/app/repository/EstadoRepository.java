package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {
    // Buscar estado por nombre
	@Query("SELECT e FROM Estado e WHERE e.nombreEstado = ?1")
    Estado findByEstado(String nombreEstado);
}
