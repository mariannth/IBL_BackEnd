package com.infinitybyteleague.app.controller;

import com.infinitybyteleague.app.exception.AppNotFoundException;
import com.infinitybyteleague.app.model.Usuario;
import com.infinitybyteleague.app.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    // Mapear el método getAll()
    @GetMapping("/usuario")
    public List<Usuario> getAllCategorias() {
        return this.usuarioService.getAll();
    }

    //Método Post manejando Status HTTP
    @PostMapping("/new-usuario")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario newUsuario ){
        if(this.usuarioService.findByEmail(newUsuario.getEmail()) != null){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(this.usuarioService.createUsuario(newUsuario));
    }

    //Mapear el método ById()
    @GetMapping("/usuario/{id}")
    public Usuario findById(@PathVariable(name="id") Integer id){
        return this.usuarioService.UsuarioById(id);
    }

    //Mapear el metodo deleteById()
    @DeleteMapping("/delete-usuario/{id}")
    public void deleteById(@PathVariable(name="id") Integer id) {
        this.usuarioService.deleteUsuario(id);
    }   

    @PutMapping("/update-usuario/{id}")
    public ResponseEntity<?> updateCategoria(@RequestBody Usuario usuarioDetails, @PathVariable (name="id") Integer id ) {
        try {
            return ResponseEntity.ok(this.usuarioService.updateUsuario(usuarioDetails, id));
        } catch (AppNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
 // Buscar usuario por email
    @GetMapping("/usuario/email/{email}")
    public ResponseEntity<Usuario> findByEmail(@PathVariable(name = "email") String email) {
        Usuario usuario = this.usuarioService.findByEmail(email);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(usuario);
    }
  
      
}