package es.upm.dit.isst.backend.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import org.hibernate.exception.DataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.UncategorizedDataAccessException;
import org.springframework.stereotype.Service;

import es.upm.dit.isst.backend.enums.EstadoPedido;
import es.upm.dit.isst.backend.model.Direccion;
import es.upm.dit.isst.backend.model.Pedido;
import es.upm.dit.isst.backend.model.Usuario;
import es.upm.dit.isst.backend.repository.PedidoRepository;
import es.upm.dit.isst.backend.repository.UsuarioRepository;
import es.upm.dit.isst.backend.service.DireccionService;
import es.upm.dit.isst.backend.service.EmpresaService;
import es.upm.dit.isst.backend.service.PedidoService;

@Service
public class PedidoServiceImpl implements PedidoService{

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    EmpresaService empresaService;

    @Autowired
    DireccionService direccionService;

    @Override
    public Pedido getPedidoByCodigo(String codigo) {
        if(!pedidoRepository.findById(codigo).isPresent()) {
            throw new IllegalArgumentException("No hay ningún pedido con el código proporcionado");
        }
        return pedidoRepository.findById(codigo).get();
    }

    @Override
    public Pedido createPedido(Pedido pedidoReq) {
        // Se comprueba que el pedidoReq es correcto, es decir, los atributos que deben estar están y que no existe ningún pedido
        // con el código proporcionado.
        if (pedidoReq.getCodigo() == null || pedidoReq.getCodigo().equalsIgnoreCase("")) {
            throw new IllegalArgumentException("El código no puede estar vacío");
        }
        if(pedidoRepository.existsById(pedidoReq.getCodigo())) {
            throw new IllegalArgumentException("Ya hay un pedido con ese código");
        }
        if (pedidoReq.getTitulo() == null || pedidoReq.getTitulo().equalsIgnoreCase("")) {
            throw new IllegalArgumentException("El título no puede estar vacío");
        }
        if(pedidoReq.getEmpresa() == null) {
            throw new IllegalArgumentException("Debe proporcionar la Empresa del pedido");
        }
        if(pedidoReq.getVehiculo() == null) {
            throw new IllegalArgumentException("Debe proporcionar el Vehículo del pedido");
        }
        // Ahora ser crea como tal el pedido y se establecen sus atributos correctamente
        Pedido newPedido = new Pedido();
        newPedido.setCodigo(pedidoReq.getCodigo()); // código
        newPedido.setTitulo(pedidoReq.getTitulo()); // título
        newPedido.setDescripcion(pedidoReq.getDescripcion()); // descripcion

        if(pedidoReq.getFecha_creacion()==null) { // fecha
            newPedido.setFecha_creacion(LocalDate.now());
        } else {
            newPedido.setFecha_creacion(pedidoReq.getFecha_creacion());
        }

        if(pedidoReq.getHora_creacion()==null) { // hora
            newPedido.setHora_creacion(LocalTime.now());
        } else {
            newPedido.setHora_creacion(pedidoReq.getHora_creacion());
        }
        newPedido.setEstado(EstadoPedido.EN_PREPARACION); // estado (siempre se crea a "EN_PREPARACION")
        newPedido.setUsuario(null); // usuario (siempre se crea a null)
        newPedido.setVehiculo(pedidoReq.getVehiculo()); // vehiculo

        newPedido.setEmpresa(pedidoReq.getEmpresa()); // empresa

        Direccion origenPedido = direccionService.getDireccion(pedidoReq.getOrigen()); // dirección origen
        if(origenPedido == null) {
            Direccion newOrigen = direccionService.createDireccion(pedidoReq.getOrigen());
            newPedido.setOrigen(newOrigen);
        } else {
            newPedido.setOrigen(origenPedido);
        }

        Direccion destinoPedido = direccionService.getDireccion(pedidoReq.getOrigen()); // direccion destino
        if(destinoPedido == null) {
            Direccion newDestino = direccionService.createDireccion(pedidoReq.getOrigen());
            newPedido.setOrigen(newDestino);
        } else {
            newPedido.setOrigen(destinoPedido);
        }
        Pedido pedidoCreado = pedidoRepository.save(newPedido);
        return pedidoCreado;
    }

    @Override
    public Pedido cambiarEstado(String codigo, EstadoPedido estadoPedido) {
        if(!pedidoRepository.existsById(codigo)) {
            throw new IllegalArgumentException("El código proporcionado no corresponde a ningún pedido");
        }
        Pedido pedido = pedidoRepository.findById(codigo).get();
        pedido.setEstado(estadoPedido);
        Pedido pedidoAct = pedidoRepository.save(pedido);
        return pedidoAct;
    }

    @Override
    public Pedido addUsuario(String codigoPedido, String usuarioId) {
        if(!pedidoRepository.existsById(codigoPedido)) {
            throw new IllegalArgumentException("El código no pertenece a ningún pedido registrado");
        }
        if(!usuarioRepository.existsById(Integer.parseInt(usuarioId))) {
            throw new IllegalArgumentException("El usuario solicitado no existe");
        }
        Pedido pedido = pedidoRepository.findById(codigoPedido).get();
        Usuario usuario = usuarioRepository.findById(Integer.parseInt(usuarioId)).get();
        if(pedido.getUsuario() != null) {
            if(pedido.getUsuario() == usuario) {
                return pedido;
            } else {
                throw new IllegalArgumentException("El pedido ya tiene un usuario asociado distinto al que lo solicita");
            }
        }
        pedido.setUsuario(usuario);
        Pedido pedidoAct = pedidoRepository.save(pedido);
        return pedidoAct;
    }
}
