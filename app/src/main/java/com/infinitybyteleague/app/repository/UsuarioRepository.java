package com.infinitybyteleague.app.repository;

import com.infinitybyteleague.app.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    // Método para buscar usuario por email
	@Query("SELECT u FROM Usuario u WHERE u.email = ?1")
    Usuario findByEmail(String email);
}
