package es.upm.dit.isst.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.backend.model.Pedido;
import es.upm.dit.isst.backend.model.Traza;

public interface TrazaRepository extends CrudRepository<Traza, Integer>{
    List<Traza> findByPedido(Pedido pedido);
}