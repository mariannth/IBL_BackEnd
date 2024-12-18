package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Producto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
	@Query("SELECT u FROM producto u WHERE u.sku = ?1")
	Producto findBySku(String sku);
	
	@Query("SELECT u FROM producto u WHERE u.stock = ?1")
	Producto findByStock(int stock);

	@Query("SELECT u FROM producto u WHERE u.marca = ?1")
	Producto findByMarca(String marca);
	
	@Query("SELECT u FROM producto u WHERE u.nombre_producto = ?1")
	Producto findByNombreProducto(String nombreProducto);
}
