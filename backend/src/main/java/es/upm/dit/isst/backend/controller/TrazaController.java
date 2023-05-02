package es.upm.dit.isst.backend.controller;

import java.net.URISyntaxException;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.backend.enums.EstadoPedido;
import es.upm.dit.isst.backend.model.Traza;
import es.upm.dit.isst.backend.repository.TrazaRepository;
import es.upm.dit.isst.backend.service.PedidoService;
import es.upm.dit.isst.backend.service.TrazaService;

@RestController
@RequestMapping("/tracking/api/trazas")
public class TrazaController {

    @Autowired
    TrazaRepository trazaRepository;

    @Autowired
    TrazaService trazaService;

    @Autowired
    PedidoService pedidoService;


    @GetMapping("")
    public ResponseEntity<?> getAllTrazas() {
        return ResponseEntity.ok().body(trazaRepository.findAll());
    }


    @GetMapping("/{trazaId}")
    public ResponseEntity<?> getTraza(@PathVariable String trazaId){
       Optional<Traza> traza = trazaRepository.findById(Integer.parseInt(trazaId));
       if(traza.isPresent()) {
        return ResponseEntity.ok().body(traza.get());
       } else {
        return ResponseEntity.ok().body("La traza solicitada no está registrada.");
       }
       }

    
    @PostMapping("")
    public ResponseEntity<?> createTraza(@RequestBody Traza trazaReq) {
        try{
            if(trazaReq == null) 
                return ResponseEntity.badRequest().body("La traza pasada no está bien");

            if(trazaRepository.existsById(trazaReq.getId())) 
                return ResponseEntity.badRequest().body("La traza ya está registrada");
            
            if(trazaRepository.findByPedido(trazaReq.getPedido()).isEmpty()) {
                pedidoService.cambiarEstado(trazaReq.getPedido().getCodigo(), EstadoPedido.EN_REPARTO);
            }
            
            Traza trazaCreada = trazaService.createTraza(trazaReq);
            return ResponseEntity.created(new URI("/tracking/api/trazas/" + trazaCreada.getId())).body(trazaCreada);
        } catch(IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(iae);
        } catch(URISyntaxException use) {
            return ResponseEntity.badRequest().body(use);
        }
    }

    @PutMapping("/{trazaId}")
    public ResponseEntity<?> modifyTraza(@PathVariable String trazaId, @RequestBody Traza trazaReq) {
        int idTraza = Integer.parseInt(trazaId);
        if(!trazaRepository.existsById(idTraza))
            return ResponseEntity.badRequest().body("La traza que quiere modificar no está registrada aún.");
        if(idTraza != trazaReq.getId()) {
            return ResponseEntity.badRequest().body("trazaId and trazaReq.getId() mismatch");
        }
        Traza trazaModificada = trazaRepository.findById(idTraza).get();
        trazaModificada.setLatitud(trazaReq.getLatitud());
        trazaModificada.setLongitud(trazaReq.getLongitud());
        trazaModificada.setFecha_creacion(trazaReq.getFecha_creacion());
        trazaModificada.setHora_creacion(trazaReq.getHora_creacion());
        trazaModificada.setPedido(trazaReq.getPedido());
        trazaModificada.setVehiculo(trazaReq.getVehiculo());
        trazaRepository.save(trazaModificada);
        return ResponseEntity.ok().body(trazaModificada);
    }

    @DeleteMapping("/{trazaId}")
    public ResponseEntity<?> deleteTraza(@PathVariable String trazaId) {
        int idTraza = Integer.parseInt(trazaId);
        if(!trazaRepository.existsById(idTraza)) {
            return ResponseEntity.badRequest().body("Esta traza ya no está registrada");
        }
        Traza trazaEliminada = trazaRepository.findById(idTraza).get();
        trazaRepository.deleteById(idTraza);
        return ResponseEntity.ok().body(trazaEliminada);
    }
    
}

