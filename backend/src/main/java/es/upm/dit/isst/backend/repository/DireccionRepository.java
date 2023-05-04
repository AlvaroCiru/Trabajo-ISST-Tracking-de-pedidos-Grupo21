package es.upm.dit.isst.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.backend.model.Direccion;

public interface DireccionRepository extends CrudRepository<Direccion, Integer>{
    List<Direccion> findByDomicilio(String domicilio);
}