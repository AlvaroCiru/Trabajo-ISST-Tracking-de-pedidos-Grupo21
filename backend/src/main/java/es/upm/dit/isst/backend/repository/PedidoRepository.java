package es.upm.dit.isst.backend.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import es.upm.dit.isst.backend.model.Empresa;
import es.upm.dit.isst.backend.model.Pedido;
import es.upm.dit.isst.backend.model.Usuario;

public interface PedidoRepository extends CrudRepository<Pedido, String>{
    List<Pedido> findByUsuario(Usuario usuario);
    List<Pedido> findByEmpresa(Empresa empresa);
    List<Pedido> findByEstado(int estado);
}
