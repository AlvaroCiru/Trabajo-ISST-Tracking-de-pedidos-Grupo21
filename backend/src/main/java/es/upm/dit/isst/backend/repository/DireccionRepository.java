package es.upm.dit.isst.backend.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import es.upm.dit.isst.backend.model.Direccion;

@Repository
public interface DireccionRepository extends CrudRepository<Direccion, Integer>{
}