package es.upm.dit.isst.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.backend.model.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Integer>{
    List<Empresa> findByNombre(String nombre);
    List<Empresa> findByEmail(String email);
    List<Empresa> findByTelefono(String telefono);
}
