package com.infinitybyteleague.app.service;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Usuario;
import com.infinitybyteleague.app.repository.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
	
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
  		
  		this.usuarioRepository = usuarioRepository;
  	}
    
    public List<Usuario> listarUsuarios() {
        return usuarioRepository.findAll();
    }


	public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    
    public Usuario obtenerUsuarioPorId(Integer id) {
        return usuarioRepository.findById(id).orElseThrow(() -> 
            new RuntimeException("Usuario no encontrado: " + id));
    }

   
    public void eliminarUsuario(Integer id) {
        usuarioRepository.deleteById(id);
    }
    
    public Usuario updateUsuario(Usuario usuario, Integer id) {
    	return this.usuarioRepository.findById(id).map(userField ->{
    		userField.setNombre(usuario.getNombre());
    		userField.setApellidoPaterno(usuario.getApellidoPaterno());
    		userField.setDireccion(usuario.getDireccion());
    		userField.setEmail(usuario.getEmail());
    		userField.setPassword(usuario.getPassword());
    		return this.usuarioRepository.save(userField);
    	}).orElseThrow(() -> new AppNotFoundException(id));
    }
}
