package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Usuario;
import com.infinitybyteleague.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

	private final UsuarioService usuarioService;

	@Autowired

	public UsuarioController(UsuarioService usuarioService) {
		this.usuarioService = usuarioService;
	}

	@GetMapping
	public List<Usuario> obtenerTodos() {
		return usuarioService.listarUsuarios();
	}

	@PostMapping("/new-user")
	public Usuario agregar(@RequestBody Usuario usuario) {
		return usuarioService.guardarUsuario(usuario);
	}

	@GetMapping("/{id}")
	public Usuario obtenerPorId(@PathVariable Integer id) {
		return usuarioService.obtenerUsuarioPorId(id);
	}

	//Mapear el metodo deleteById()
		@DeleteMapping("/delete-user/{id}")
		public void deleteById(@PathVariable(name = "id") Integer id) {
			this.usuarioService.eliminarUsuario(id);
		}
		
	@PutMapping("/update-user/{id}")
	public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario, @PathVariable(name = "id") Integer id){
		try {
			return ResponseEntity.ok(this.usuarioService.updateUsuario(usuario, id));
		}catch(AppNotFoundException e) {
			return ResponseEntity.notFound().build();
		}
	}
}
