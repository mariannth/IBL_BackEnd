package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Pago;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PagoRepository extends JpaRepository<Pago, Integer> {
    // Puedes agregar métodos personalizados si es necesario
	@Query("SELECT p FROM Pago p WHERE p.folioFactura = ?1")
    Pago findByFolioFactura(String folioFactura);
	
}
