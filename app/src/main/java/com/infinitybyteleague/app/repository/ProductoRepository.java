package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Método para buscar producto por nombre
	@Query("SELECT p FROM Producto p WHERE p.nombreProducto = ?1")
    Producto findByNombreProducto(String nombreProducto);
}