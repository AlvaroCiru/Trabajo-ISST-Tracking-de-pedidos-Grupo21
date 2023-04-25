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

import es.upm.dit.isst.backend.model.Vehiculo;
import es.upm.dit.isst.backend.repository.VehiculoRepository;

@RestController
@RequestMapping("/tracking/api/vehiculos")
public class VehiculoController {

    @Autowired
    VehiculoRepository vehiculoRepository;


    @GetMapping("")
    public ResponseEntity<?> getAllVehiculos() {
        return ResponseEntity.ok().body(vehiculoRepository.findAll());
    }


    @GetMapping("/{vehiculoId}")
    public ResponseEntity<?> getVehiculo(@PathVariable String vehiculoId){
       Optional<Vehiculo> vehiculo = vehiculoRepository.findById(Integer.parseInt(vehiculoId));
       if(vehiculo.isPresent()) {
        return ResponseEntity.ok().body(vehiculo.get());
       } else {
        return ResponseEntity.ok().body("El vehículo solicitado no está registrado.");
       }
       }

    
    @PostMapping("")
    public ResponseEntity<?> createVehiculo(@RequestBody Vehiculo vehiculoReq) {
        try{
            if(vehiculoReq == null) 
                return ResponseEntity.badRequest().body("El vehículo pasado no está bien");

            if(vehiculoRepository.existsById(vehiculoReq.getId())) 
                return ResponseEntity.badRequest().body("El vehículo ya está registrado");    

            vehiculoRepository.save(vehiculoReq);
            return ResponseEntity.created(new URI("/tracking/api/vehiculos/" + vehiculoReq.getId())).body(vehiculoReq);
        } catch(IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(iae);
        } catch(URISyntaxException use) {
            return ResponseEntity.badRequest().body(use);
        }
    }

    @PutMapping("/{vehiculoId}")
    public ResponseEntity<?> modifyVehiculo(@PathVariable String vehiculoId, @RequestBody Vehiculo vehiculoReq) {
        int idVehiculo = Integer.parseInt(vehiculoId);
        if(!vehiculoRepository.existsById(idVehiculo))
            return ResponseEntity.badRequest().body("El vehículo que quiere modificar no está registrado aún.");
        if(idVehiculo != vehiculoReq.getId()) {
            return ResponseEntity.badRequest().body("vehiculoId and vehiculoReq.getId() mismatch");
        }
        Vehiculo vehiculoModificado = vehiculoRepository.findById(idVehiculo).get();
        vehiculoModificado.setMatricula(vehiculoReq.getMatricula());
        vehiculoModificado.setModelo(vehiculoReq.getModelo());
        vehiculoModificado.setTelefono(vehiculoReq.getTelefono());
        vehiculoRepository.save(vehiculoModificado);
        return ResponseEntity.ok().body(vehiculoModificado);
    }

    @DeleteMapping("/{vehiculoId}")
    public ResponseEntity<?> deleteVehiculo(@PathVariable String vehiculoId) {
        int idVehiculo = Integer.parseInt(vehiculoId);
        if(!vehiculoRepository.existsById(idVehiculo)) {
            return ResponseEntity.badRequest().body("Este vehículo ya no está registrado");
        }
        Vehiculo vehiculoEliminado = vehiculoRepository.findById(idVehiculo).get();
        vehiculoRepository.deleteById(idVehiculo);
        return ResponseEntity.ok().body(vehiculoEliminado);
    }
    
}

