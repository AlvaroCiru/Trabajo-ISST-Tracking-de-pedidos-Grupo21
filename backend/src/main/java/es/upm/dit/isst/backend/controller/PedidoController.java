package es.upm.dit.isst.backend.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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
import es.upm.dit.isst.backend.model.Empresa;
import es.upm.dit.isst.backend.model.Pedido;
import es.upm.dit.isst.backend.model.Usuario;
import es.upm.dit.isst.backend.repository.EmpresaRepository;
import es.upm.dit.isst.backend.repository.PedidoRepository;
import es.upm.dit.isst.backend.repository.UsuarioRepository;
import es.upm.dit.isst.backend.service.PedidoService;

import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/tracking/api/pedidos")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    PedidoService pedidoService;

    @Autowired
    UsuarioRepository usuarioRepository;

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

    @GetMapping("/compradores/{compradorId}")
    public ResponseEntity<?> getPedidosByComprador(@PathVariable String compradorId) {
        try {
            Optional<Usuario> comprador = usuarioRepository.findById(Integer.parseInt(compradorId));

            if(comprador.isPresent()) {
                return ResponseEntity.badRequest().body("El comprador no existe");
            }

            return ResponseEntity.ok().body(pedidoRepository.findByUsuario(comprador.get()));
        } catch(RuntimeException exc) {
            return ResponseEntity.badRequest().body(exc);
        }
    }

    @GetMapping("/gestores/{gestorId}")
    public ResponseEntity<?> getPedidosByEmpresa(@PathVariable String gestorId) {
        try {

            if(!usuarioRepository.findById(Integer.parseInt(gestorId)).isPresent()) {
                return ResponseEntity.badRequest().body("Ese gestor no existe");
            }

            Usuario gestor = usuarioRepository.findById(Integer.parseInt(gestorId)).get();

            if(!gestor.isEs_gestor()) {
                return ResponseEntity.badRequest().body("Este usuario no es gestor");
            }

            Empresa empresa = gestor.getEmpresa();

            return ResponseEntity.ok().body(pedidoRepository.findByEmpresa(empresa));
        } catch(RuntimeException exc) {
            return ResponseEntity.badRequest().body(exc);
        }
    }

    @PostMapping("/crearVariosPedidos")
    public ResponseEntity<?> createVariosPedidos(@RequestBody List<Pedido> pedidosReq) {
        List<Pedido> newPedidos = new ArrayList<Pedido>();
        for(Pedido pedido : pedidosReq) {
            Pedido newPedido = pedidoService.createPedido(pedido);
            newPedidos.add(newPedido);
        }
        return ResponseEntity.ok().body(newPedidos);
    }

    @PostMapping("/crearPedido")
    public ResponseEntity<?> createPedido(@RequestBody Pedido pedidoReq) {
        try {
            Pedido pedidoCreado = pedidoService.createPedido(pedidoReq);
            return ResponseEntity.created(new URI("/api/pedidos/" + pedidoCreado.getCodigo())).body(pedidoCreado);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(iae.getMessage());
        } catch (URISyntaxException use) {
            return ResponseEntity.badRequest().body(use.getMessage());
        }
    }

    @PutMapping("/agregarPedido/{compradorId}")
    public ResponseEntity<?> addUsuarioToPedido(@PathVariable String compradorId, @RequestBody String codigoPedido) {
        try{
            if(compradorId == null || compradorId.equalsIgnoreCase("") || 
            codigoPedido == null || codigoPedido.equalsIgnoreCase("")) {
                return ResponseEntity.badRequest().body("Los parámetros no pueden estar vacíos");
            }
            Pedido pedidoAct = pedidoService.addUsuario(codigoPedido, compradorId);
            return ResponseEntity.ok().body(pedidoAct);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(iae.getMessage());
        }
        
    }

    @PutMapping("/{pedidoId}/deliver")
    public ResponseEntity<?> iniciarReparto(@PathVariable String pedidoId) {
        Pedido pedidoAct = pedidoService.cambiarEstado(pedidoId, EstadoPedido.EN_REPARTO);
        return ResponseEntity.ok().body(pedidoAct);
    }

    @PutMapping("/{pedidoId}/finish")
    public ResponseEntity<?> finalizarPedido(@PathVariable String pedidoId) {
        Pedido pedidoAct = pedidoService.cambiarEstado(pedidoId, EstadoPedido.FINALIZADO);
        return ResponseEntity.ok().body(pedidoAct);
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
