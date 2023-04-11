package es.upm.dit.isst.backend.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.backend.model.Pedido;
import es.upm.dit.isst.backend.repository.EmpresaRepository;
import es.upm.dit.isst.backend.repository.PedidoRepository;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping("/pedidos")
    public List<Pedido> getAllPedidos() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    @GetMapping("/pedidos/{pedidoId}")
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

    @PostMapping("/pedidos")
    public ResponseEntity<?> createPedido(@RequestBody Pedido pedido) {
        try {
            Pedido newPedido = new Pedido();
            if (pedido == null) {
                return ResponseEntity.badRequest().body("No se ha proporcionado ningún pedido");
            }
            if(pedidoRepository.findById(pedido.getCodigo()).isPresent()) {
                return ResponseEntity.ok().body("El pedido proporcionado ya existe");
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
            newPedido.setFechaCreacion(LocalDate.now());
            newPedido.setHoraCreacion(LocalTime.now());
            newPedido.setEstado(0);
            newPedido.setUsuario(pedido.getUsuario());
            newPedido.setEmpresa(pedido.getEmpresa());
            newPedido.setVehiculo(pedido.getVehiculo());
            newPedido.setOrigen(pedido.getOrigen());
            newPedido.setDestino(pedido.getDestino());
            Pedido pedidoCreado = pedidoRepository.save(pedido);
            return ResponseEntity.created(new URI("/pedidos/" + pedidoCreado.getCodigo())).body(pedidoCreado);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(iae);
        } catch (URISyntaxException use) {
            return ResponseEntity.badRequest().body(use);
        }
        
    }

    @PutMapping(value="pedidos/{pedidoId}")
    public ResponseEntity<?> putPedido(@PathVariable String pedidoId, @RequestBody Pedido pedidoReq) {
        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
        if (pedido == null) {
            return ResponseEntity.badRequest().body("No hay ningún pedido con el código proporcionado");
        }
        
        return new ResponseEntity<>(pedidoReq, HttpStatus.OK);
    }
    
    @DeleteMapping(value="pedidos/{pedidoId}")
    public ResponseEntity<?> deletePedido(@PathVariable String pedidoId) {
        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
        if(!pedido.isPresent()) {
            return ResponseEntity.ok().body("No existe ese pedido");
        }
        pedidoRepository.deleteById(pedidoId);
        return ResponseEntity.ok().body(pedido.get());
    }

}
