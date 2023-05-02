package es.upm.dit.isst.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.backend.model.Vehiculo;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer>{
    List<Vehiculo> findByMatricula(String matricula);
}
