package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Pedido;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    // Puedes agregar métodos personalizados si es necesario
	@Query("SELECT p FROM Pedido p WHERE p.estadoDePedido = ?1")
    List<Pedido> findByEstadoPedido(String estadoPedido);
}
