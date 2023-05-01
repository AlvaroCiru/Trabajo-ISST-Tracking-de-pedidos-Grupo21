package es.upm.dit.isst.backend.controller;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
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
import es.upm.dit.isst.backend.repository.EmpresaRepository;
import es.upm.dit.isst.backend.repository.UsuarioRepository;

@RestController
@RequestMapping("/tracking/api/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EmpresaRepository empresaRepository;
    
    @GetMapping("")
    public ResponseEntity<?> getAllUsuarios() {
        return ResponseEntity.ok().body(usuarioRepository.findAll());
    }

    @GetMapping("/{usuarioId}")
    public ResponseEntity<?> getUsuario(@PathVariable String usuarioId) {
        Usuario usuario = usuarioRepository.findById(Integer.parseInt(usuarioId)).get();
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/compradores")
    public ResponseEntity<?> createComprador(@RequestBody Usuario usuarioReq) {
        
        try {
            Usuario newUsuario = new Usuario();
            if (usuarioReq == null) {
                return ResponseEntity.badRequest().body("No se ha proporcionado ningún usuarioReq");
            }
            
            if(usuarioReq.getEmail() == null || usuarioReq.getEmail().equalsIgnoreCase("") || 
                usuarioReq.getNombre() == null || usuarioReq.getNombre().equalsIgnoreCase("") ||
                usuarioReq.getContrasena() == null || usuarioReq.getContrasena().equalsIgnoreCase("") ||
                usuarioReq.getTelefono() == null || usuarioReq.getTelefono().equalsIgnoreCase("")) {
                return ResponseEntity.badRequest().body("Incorrect arguments");
            }

            if(!usuarioRepository.findByEmail(usuarioReq.getEmail()).isEmpty()) {
                return ResponseEntity.badRequest().body("Ese email ya está en uso");
            }

            if(!usuarioRepository.findByNombre(usuarioReq.getNombre()).isEmpty()) {
                return ResponseEntity.badRequest().body("Ese nombre de usuario ya está en uso");
            }

            if(!usuarioRepository.findByTelefono(usuarioReq.getTelefono()).isEmpty()) {
                return ResponseEntity.badRequest().body("Ese teléfono de usuario ya está en uso");
            }
            
            newUsuario.setNombre(usuarioReq.getNombre());
            newUsuario.setEmail(usuarioReq.getEmail());
            newUsuario.setContrasena(usuarioReq.getContrasena());
            newUsuario.setTelefono(usuarioReq.getTelefono());
            newUsuario.setEs_gestor(false);
            newUsuario.setEmpresa(null);

            usuarioRepository.save(newUsuario);
            return ResponseEntity.created(new URI("/easytrack/api/usuarios/" + usuarioReq.getId())).body(newUsuario);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body("Illegal Argument Exception");
        } catch (DataIntegrityViolationException dive) {
            return ResponseEntity.badRequest().body("El nombre, el email o el teléfono que ha proporcionado ya están asociados a otro usuario");
        } catch (URISyntaxException use) {
            return ResponseEntity.badRequest().body("URI Syntax Exception");
        }
    }

    @PostMapping("/gestores")
    public ResponseEntity<?> createGestor(@RequestBody Usuario usuarioReq) {
        
        try {
            Usuario newUsuario = new Usuario();
            if (usuarioReq == null) {
                return ResponseEntity.badRequest().body("No se ha proporcionado ningún usuarioReq");
            }
            
            if(usuarioReq.getEmail() == null || usuarioReq.getEmail().equalsIgnoreCase("") || 
                usuarioReq.getNombre() == null || usuarioReq.getNombre().equalsIgnoreCase("") ||
                usuarioReq.getContrasena() == null || usuarioReq.getContrasena().equalsIgnoreCase("") ||
                usuarioReq.getTelefono() == null || usuarioReq.getTelefono().equalsIgnoreCase("")) {
                return ResponseEntity.badRequest().body("Incorrect arguments");
            }

            if(!usuarioRepository.findByEmail(usuarioReq.getEmail()).isEmpty()) {
                return ResponseEntity.badRequest().body("User provided already exists");
            }
            
            newUsuario.setNombre(usuarioReq.getNombre());
            newUsuario.setEmail(usuarioReq.getEmail());
            newUsuario.setContrasena(usuarioReq.getContrasena());
            newUsuario.setTelefono(usuarioReq.getTelefono());
            newUsuario.setEs_gestor(true);

            if(usuarioReq.getEmpresa() == null) {
                return ResponseEntity.badRequest().body("Los gestores deben tener una empresa asociada");
            }

            if(empresaRepository.findByNombre(usuarioReq.getEmpresa().getNombre()).isEmpty()) {
                return ResponseEntity.badRequest().body("Los gestores deben estar asociados a una empresa existente.");
            } else {
                newUsuario.setEmpresa(empresaRepository.findByNombre(usuarioReq.getEmpresa().getNombre()).get(0));
            }

            usuarioRepository.save(newUsuario);
            return ResponseEntity.created(new URI("/easytrack/api/usuarios/" + usuarioReq.getId())).body(newUsuario);
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
