package es.upm.dit.isst.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.backend.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
    List<Usuario> findByNombre(String nombre);
    List<Usuario> findByEmail(String email);
    List<Usuario> findByTelefono(String telefono);
}
