package es.upm.dit.isst.backend.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.backend.model.Usuario;
import es.upm.dit.isst.backend.repository.UsuarioRepository;

@RestController
@RequestMapping("/tracking/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @GetMapping("")
    public ResponseEntity<?> getAllUsuarios() {
        return ResponseEntity.ok().body(usuarioRepository.findAll());
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> getUsuario(@PathVariable String usuarioId) {
        Usuario usuario = usuarioRepository.findById(Integer.parseInt(usuarioId)).get();
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuarioReq) {
        
        try {
            Usuario newUsuario = new Usuario();
            if (usuarioReq == null) {
                return ResponseEntity.badRequest().body("No se ha proporcionado ningún usuarioReq");
            }
            if(usuarioRepository.findById(usuarioReq.getId()).isPresent()) {
                return ResponseEntity.badRequest().body("El usuarioReq proporcionado ya existe");
            }
            if (usuarioReq.getId() == 0) {
                return ResponseEntity.badRequest().body("El código no puede estar vacío");
            } else {
                newUsuario.setId(usuarioReq.getId());
            }
            if (usuarioReq.getNombre() == null || usuarioReq.getNombre().equals("")) {
                return ResponseEntity.badRequest().body("El título no puede estar vacío");
            } else {
                newUsuario.setNombre(usuarioReq.getNombre());
            }
            newUsuario.setEmail(usuarioReq.getEmail());
            newUsuario.setContrasena(usuarioReq.getContrasena());
            newUsuario.setTelefono(usuarioReq.getTelefono());
            newUsuario.setEs_gestor(usuarioReq.isEs_gestor() ? true : false);
            newUsuario.setEmpresa(usuarioReq.isEs_gestor() ? usuarioReq.getEmpresa() : 0);
            usuarioRepository.save(newUsuario);
            return ResponseEntity.created(new URI("/tracking/api/usuarios/" + usuarioReq.getId())).body(newUsuario);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(iae);
        } catch (URISyntaxException use) {
            return ResponseEntity.badRequest().body(use);
        }
    }

    @PutMapping("/{usuarioId}")
    public ResponseEntity<?> modifyUsuario(@PathVariable String usuarioId, @RequestBody Usuario usuarioReq) {

        return null;
    }

    @DeleteMapping("/{usuarioId}")
    public ResponseEntity<?> deleteUsuario(@PathVariable String usuarioId) {
        int idUsuario = Integer.parseInt(usuarioId);
        if(!usuarioRepository.existsById(idUsuario)) {
            return ResponseEntity.badRequest().body("Ese usuario no está registrado");
        }
        Usuario usuarioEliminado = usuarioRepository.findById(idUsuario).get();
        usuarioRepository.deleteById(idUsuario);
        return ResponseEntity.ok().body(usuarioEliminado);
    }
}
