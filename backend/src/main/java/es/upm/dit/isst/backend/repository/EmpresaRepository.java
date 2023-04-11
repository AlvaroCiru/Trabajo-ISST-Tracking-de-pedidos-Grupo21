package es.upm.dit.isst.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.backend.model.Empresa;

public interface EmpresaRepository extends CrudRepository<Empresa, Integer>{
}
