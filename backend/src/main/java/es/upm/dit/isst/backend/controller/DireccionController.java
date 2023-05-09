package es.upm.dit.isst.backend.controller;

import java.net.URISyntaxException;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.backend.model.Direccion;
import es.upm.dit.isst.backend.repository.DireccionRepository;
import es.upm.dit.isst.backend.service.DireccionService;

@CrossOrigin
@RestController
@RequestMapping("/tracking/api/direcciones")
public class DireccionController {

    @Autowired
    DireccionRepository direccionRepository;

    @Autowired
    DireccionService direccionService;
    
    @GetMapping("")
    public ResponseEntity<?> getAllDireccion() {
        return ResponseEntity.ok().body(direccionRepository.findAll());
    }

    @GetMapping("/{direccionId}")
    public ResponseEntity<?> getDireccion(@PathVariable String direccionId) {
        int idDireccion = Integer.parseInt(direccionId);
        Optional<Direccion> direccion = direccionRepository.findById(idDireccion);
        if (direccion.isPresent()) {
            return ResponseEntity.ok().body(direccion.get());
        } else {
            return ResponseEntity.ok().body("La direccion pedida no existe");
        }
        
    }

    @PostMapping("")
    public ResponseEntity<?> createEmpresa(@RequestBody Direccion direccionReq) {
        try{
            if(direccionReq == null) 
                return ResponseEntity.badRequest().body("La dirección pasada no es correcta");

            if(direccionRepository.existsById(direccionReq.getId())) 
                return ResponseEntity.badRequest().body("La dirección ya está registrada");    

            Direccion newDireccion = direccionService.createDireccion(direccionReq);
            // direccionRepository.save(direccionReq);
            return ResponseEntity.created(new URI("/tracking/api/direcciones/" + direccionReq.getId())).body(newDireccion);
        } catch(IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(iae);
        } catch(URISyntaxException use) {
            return ResponseEntity.badRequest().body(use);
        }
    }

    @PutMapping("/{direccionId}")
    public ResponseEntity<?> modifyEmpresa(@PathVariable String direccionId, @RequestBody Direccion direccionReq) {
        int idDireccion = Integer.parseInt(direccionId);
        if(!direccionRepository.existsById(idDireccion))
            return ResponseEntity.badRequest().body("La dirección a modificar no esta registrada.");
        if(idDireccion != direccionReq.getId()) {
            return ResponseEntity.badRequest().body("DireccionId and direccionReq.getId() mismatch");
        }
        Direccion direccionModificada = direccionRepository.findById(idDireccion).get();
        direccionModificada.setLatitud(direccionReq.getLatitud());
        direccionModificada.setLongitud(direccionReq.getLongitud());
        direccionModificada.setProvincia(direccionReq.getProvincia());
        direccionModificada.setCiudad(direccionReq.getCiudad());
        direccionModificada.setDomicilio(direccionReq.getDomicilio());
        direccionModificada.setPostal_code(direccionReq.getPostal_code());
        direccionRepository.save(direccionModificada);
        return ResponseEntity.ok().body(direccionModificada);
    }

    @DeleteMapping("/{direccionId}")
    public ResponseEntity<?> deleteEmpresa(@PathVariable String direccionId) {
        int idDireccion = Integer.parseInt(direccionId);
        if(!direccionRepository.existsById(idDireccion)) {
            return ResponseEntity.badRequest().body("Esta dirección ya no esta registrada.");
        }
        Direccion DireccionEliminada = direccionRepository.findById(idDireccion).get();
        direccionRepository.deleteById(idDireccion);
        return ResponseEntity.ok().body(DireccionEliminada);
    }
}

