package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Pais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PaisRepository extends JpaRepository<Pais, Integer> {
	@Query("SELECT u FROM Pais u WHERE u.nombre_pais = ?1")
	Pais findByPais(String nombrePais);
}
