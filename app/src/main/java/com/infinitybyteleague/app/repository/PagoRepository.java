package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Pago;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository<Pago, Integer> {
	
	@Query("SELECT u FROM pago u WHERE u.fecha = ?1")
	Pago findByFechaPago(Date fecha);
	
	@Query("SELECT u FROM pago u WHERE u.tipo_de_pago = ?1")
	Pago findByTipoPago(String tipoDePago);
	
	@Query("SELECT u FROM pago u WHERE u.folio_factura = ?1")
	Pago findByFolioFactura(String folioFactura);
}

