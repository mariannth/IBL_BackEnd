package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Municipio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MunicipioRepository extends JpaRepository<Municipio, Integer> {

	@Query("SELECT u FROM municipios u WHERE u.nombre_municipios = ?1")
	Municipio findByMunicipio(String nombreMunicipio);
}
