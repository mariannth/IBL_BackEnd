package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Pedido;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
	@Query("SELECT u FROM pedido u WHERE u.fecha = ?1")
	Pedido findByFecha(Date fecha);
	
	@Query("SELECT u FROM pedido u WHERE u.estado_de_pedido = ?1")
	Pedido findByEstadoP(String estadoDePedido);
	
}
