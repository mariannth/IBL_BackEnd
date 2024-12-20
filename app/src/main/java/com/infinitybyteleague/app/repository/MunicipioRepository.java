package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {
    // Puedes agregar métodos personalizados si es necesario
	@Query("SELECT m FROM Municipio m WHERE m.nombreMunicipio = ?1")
	Municipio findByNombreMunicipio(String nombreMunicipio);

}