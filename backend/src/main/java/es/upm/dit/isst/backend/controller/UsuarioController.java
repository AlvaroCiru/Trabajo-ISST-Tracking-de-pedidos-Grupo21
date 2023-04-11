package es.upm.dit.isst.backend.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.backend.model.Usuario;
import es.upm.dit.isst.backend.repository.UsuarioRepository;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;
    
    @GetMapping("/usuarios")
    public ResponseEntity<?> getAllUsuarios() {
        return ResponseEntity.ok().body(usuarioRepository.findAll());
    }

    @GetMapping("/usuarios/{usuarioId}")
    public ResponseEntity<?> getUsuario(@PathVariable String usuarioId) {
        Usuario usuario = usuarioRepository.findById(Integer.parseInt(usuarioId)).get();
        return ResponseEntity.ok().body(usuario);
    }

    @PostMapping("/usuarios")
    public ResponseEntity<?> createUsuario(@RequestBody Usuario usuarioReq) {
        Usuario newUsuario = new Usuario();
        // Comprobación de que el usuario ofrecido existe y que no está ya en la base de datos.
        if(usuarioReq==null) {
            return ResponseEntity.badRequest().body("El usuario que me has pasado no está bien");
        }
        if(usuarioRepository.existsById(usuarioReq.getId())) {
            return ResponseEntity.badRequest().body("El usuario ya está registrado");
        }
        //Crear el nuevo usuario
        newUsuario.setId(usuarioReq.getId());
        newUsuario.setNombre(usuarioReq.getNombre());
        newUsuario.setApellido(usuarioReq.getApellido());
        newUsuario.setEmail(usuarioReq.getEmail());
        newUsuario.setContrasena(usuarioReq.getContrasena());
        newUsuario.setTelefono(usuarioReq.getTelefono());
        newUsuario.setEsGestor(usuarioReq.isEsGestor() ? true : false);
        newUsuario.setEmpresa(usuarioReq.getEmpresa());

        System.out.println(Integer.toString(newUsuario.getId()));
        System.out.println(newUsuario.getNombre());
        System.out.println(newUsuario.getApellido());
        System.out.println(newUsuario.getEmail());
        System.out.println(newUsuario.getContrasena());
        System.out.println(newUsuario.getTelefono());
        System.out.println(Boolean.toString(newUsuario.isEsGestor()));
        System.out.println(Boolean.toString(newUsuario.isEsGestor()));
        System.out.println(Integer.toString(newUsuario.getEmpresa()));

        System.out.println(Integer.toString(usuarioReq.getId()));
        System.out.println(usuarioReq.getNombre());
        System.out.println(usuarioReq.getApellido());
        System.out.println(usuarioReq.getEmail());
        System.out.println(usuarioReq.getContrasena());
        System.out.println(usuarioReq.getTelefono());
        System.out.println(Boolean.toString(usuarioReq.isEsGestor()));
        System.out.println(Boolean.toString(usuarioReq.isEsGestor()));
        System.out.println(Integer.toString(usuarioReq.getEmpresa()));
        usuarioRepository.save(usuarioReq);
        return ResponseEntity.ok().body(usuarioReq);
    }

    @PutMapping("/usuarios/{usuarioId}")
    public ResponseEntity<?> modifyUsuario(@PathVariable String usuarioId, @RequestBody Usuario usuarioReq) {

        return null;
    }

    @DeleteMapping("/usuarios/{usuarioId}")
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
