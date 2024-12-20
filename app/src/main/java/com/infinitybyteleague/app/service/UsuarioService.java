package com.infinitybyteleague.app.service;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Usuario;
import com.infinitybyteleague.app.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    public Usuario createUsuario(Usuario newUsuario) {
        return this.usuarioRepository.save(newUsuario);
    }
    
    public Usuario UsuarioById(Integer id) {
        return this.usuarioRepository
        		.findById(id)
        		.orElseThrow(()-> new AppNotFoundException(id));
    }

   public Usuario findByEmail(String email) {
	   return this.usuarioRepository.findByEmail(email);
   }

    public Usuario updateUsuario(Usuario usuario, Integer id){
        return this.usuarioRepository.findById(id).map(usuarioField ->{
        	usuarioField.setNombre(usuario.getNombre());
        	usuarioField.setApellidoPaterno(usuario.getApellidoPaterno());
        	usuarioField.setApellidoMaterno(usuario.getApellidoMaterno());
        	usuarioField.setDireccion(usuario.getDireccion());
        	usuarioField.setEmail(usuario.getEmail());
        	usuarioField.setPassword(usuario.getPassword());
        	usuarioField.setRfc(usuario.getRfc());
        	usuarioField.setTelefono(usuario.getTelefono());
        	usuarioField.setTipoUsuario(usuario.getTipoUsuario());
        	return usuarioRepository.save(usuarioField);
        }).orElseThrow(()-> new AppNotFoundException(id));
                
        
        
    }

    public void deleteUsuario(Integer id) {
        if (this.usuarioRepository.existsById(id)) {
        	this.usuarioRepository.deleteById(id);
        }else {
        	throw new AppNotFoundException(id);
        }
    }
}