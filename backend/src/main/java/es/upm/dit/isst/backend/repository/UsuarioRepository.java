package es.upm.dit.isst.backend.repository;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.backend.model.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Integer>{
}
