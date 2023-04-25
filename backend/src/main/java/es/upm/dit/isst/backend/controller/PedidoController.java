package es.upm.dit.isst.backend.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
// import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.catalina.connector.Response;
// import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.backend.enums.EstadoPedido;
import es.upm.dit.isst.backend.model.Pedido;
import es.upm.dit.isst.backend.repository.EmpresaRepository;
import es.upm.dit.isst.backend.repository.PedidoRepository;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/tracking/api/pedidos")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping("")
    public List<Pedido> getAllPedidos() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<?> getPedido(@PathVariable String pedidoId) {
        try {
            Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
            if (!pedido.isPresent()) {
                return ResponseEntity.ok().body("No existe el pedido");
            }
            return ResponseEntity.ok().body(pedido);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.ok().body(iae);
        }
    }

    

    @PostMapping("")
    public ResponseEntity<?> createPedido(@RequestBody Pedido pedido) {
        try {
            Pedido newPedido = new Pedido();
            if (pedido == null) {
                return ResponseEntity.badRequest().body("No se ha proporcionado ningún pedido");
            }
            if(pedidoRepository.findById(pedido.getCodigo()).isPresent()) {
                return ResponseEntity.badRequest().body("El pedido proporcionado ya existe");
            }
            if (pedido.getCodigo() == null || pedido.getCodigo().equals("")) {
                return ResponseEntity.badRequest().body("El código no puede estar vacío");
            } else {
                newPedido.setCodigo(pedido.getCodigo());
            }
            if (pedido.getTitulo() == null || pedido.getTitulo().equals("")) {
                return ResponseEntity.badRequest().body("El título no puede estar vacío");
            } else {
                newPedido.setTitulo(pedido.getTitulo());
            }
            newPedido.setDescripcion(pedido.getDescripcion());
            if(pedido.getFecha_creacion()==null) {
                newPedido.setFecha_creacion(LocalDate.now());
            } else {
                newPedido.setFecha_creacion(pedido.getFecha_creacion());
            }
            if(pedido.getHora_creacion()==null) {
                newPedido.setHora_creacion(LocalTime.now());
            } else {
                newPedido.setHora_creacion(pedido.getHora_creacion());
            }
            newPedido.setEstado(EstadoPedido.EN_PREPARACION);
            newPedido.setUsuario(null);
            newPedido.setEmpresa(pedido.getEmpresa());
            newPedido.setVehiculo(pedido.getVehiculo());
            newPedido.setOrigen(pedido.getOrigen());
            newPedido.setDestino(pedido.getDestino());
            Pedido pedidoCreado = pedidoRepository.save(newPedido);
            return ResponseEntity.created(new URI("/api/pedidos/" + pedidoCreado.getCodigo())).body(pedidoCreado);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(iae);
        } catch (URISyntaxException use) {
            return ResponseEntity.badRequest().body(use);
        }
    }

    @PutMapping("/{pedidoId}")
    public ResponseEntity<?> modifyEmpresa(@PathVariable String pedidoId, @RequestBody Pedido pedidoReq) {
        String idPedido = pedidoId;
        if(!pedidoRepository.existsById(idPedido)) {
            return ResponseEntity.badRequest().body("La empresa que quiere modificar no está registrada.");
        }
        if(!idPedido.equals(pedidoReq.getCodigo())) {
            return ResponseEntity.badRequest().body("empresaId and empresaReq.getId() mismatch");
        }
        Pedido pedidoModificado = pedidoRepository.findById(idPedido).get();
        pedidoModificado.setTitulo(pedidoReq.getTitulo());
        pedidoModificado.setDescripcion(pedidoReq.getDescripcion());
        pedidoModificado.setFecha_creacion(pedidoReq.getFecha_creacion());
        pedidoModificado.setHora_creacion(pedidoModificado.getHora_creacion());
        pedidoModificado.setEstado(pedidoReq.getEstado());
        pedidoModificado.setVehiculo(pedidoReq.getVehiculo());
        pedidoModificado.setUsuario(pedidoReq.getUsuario());
        pedidoModificado.setEmpresa(pedidoReq.getEmpresa());
        pedidoModificado.setOrigen(pedidoReq.getOrigen());
        pedidoModificado.setDestino(pedidoReq.getDestino());
        pedidoRepository.save(pedidoModificado);
        return ResponseEntity.ok().body(pedidoModificado);
    }
    
    @DeleteMapping("/{pedidoId}")
    public ResponseEntity<?> deletePedido(@PathVariable String pedidoId) {
        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
        if(!pedido.isPresent()) {
            return ResponseEntity.ok().body("No existe ese pedido");
        }
        pedidoRepository.deleteById(pedidoId);
        return ResponseEntity.ok().body(pedido.get());
    }

    @DeleteMapping("")
    public ResponseEntity<?> deleteAllPedidos() {
        pedidoRepository.deleteAll();
        return ResponseEntity.ok().body("Todos los pedidos han sido eliminados");
    }

}
