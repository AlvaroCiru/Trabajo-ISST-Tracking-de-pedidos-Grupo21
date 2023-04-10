package es.upm.dit.isst.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.backend.model.Comprador;
import es.upm.dit.isst.backend.model.Usuario;

public interface CompradorRepository extends CrudRepository<Comprador, Integer>{
    List<Comprador> findById(int id);
    List<Comprador> findByUsuario(Usuario usuario);
}
