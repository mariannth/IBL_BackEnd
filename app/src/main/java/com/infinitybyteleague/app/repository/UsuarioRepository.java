package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
	//Crear métodos Query de JPA
		@Query("SELECT u FROM usuario u WHERE u.email = ?1")
		Usuario findByEmail(String email);
		
		@Query("SELECT u FROM usuario u WHERE u.nombre = ?1")
		Usuario findByName(String nombre);
		
		@Query("SELECT u FROM usuario u WHERE u.apellido_materno = ?1")
		Usuario findByAPM(String apellidoMaterno);
	
		@Query("SELECT u FROM usuario u WHERE u.apellido_paterno = ?1")
		Usuario findByAPP(String apellidoPaterno);
		
		@Query("SELECT u FROM usuario u WHERE u.rfc = ?1")
		Usuario findByRfc(String rfc);
		
		@Query("SELECT u FROM usuario u WHERE u.telefono = ?1")
		Usuario findByTelefono(String telefono);
		
}
