package es.upm.dit.isst.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.backend.model.Comprador;
import es.upm.dit.isst.backend.model.Empresa;
import es.upm.dit.isst.backend.model.Pedido;

public interface PedidoRepository extends CrudRepository<Pedido, String>{
    List<Pedido> findByComprador(Comprador comprador);
    List<Pedido> findByEmpresa(Empresa empresa);
    List<Pedido> findByEstado(int estado);
}
