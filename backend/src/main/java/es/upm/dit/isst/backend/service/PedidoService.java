package es.upm.dit.isst.backend.service;

import es.upm.dit.isst.backend.enums.EstadoPedido;
import es.upm.dit.isst.backend.model.Pedido;

public interface PedidoService {
    public Pedido createPedido(Pedido pedido);
    public Pedido cambiarEstado (String codigo, EstadoPedido estado);
}
