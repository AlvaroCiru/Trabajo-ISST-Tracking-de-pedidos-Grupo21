package es.upm.dit.isst.backend.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
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

import es.upm.dit.isst.backend.model.Direccion;
import es.upm.dit.isst.backend.model.Empresa;
import es.upm.dit.isst.backend.repository.DireccionRepository;
import es.upm.dit.isst.backend.repository.EmpresaRepository;
import es.upm.dit.isst.backend.service.DireccionService;
import es.upm.dit.isst.backend.service.EmpresaService;


@RestController
@RequestMapping("/tracking/api/empresas")
public class EmpresaController {

    @Autowired
    EmpresaRepository empresaRepository;

    @Autowired
    EmpresaService empresaService;

    @Autowired
    DireccionService direccionService;
    
    @GetMapping("")
    public ResponseEntity<?> getAllEmpresas() {
        return ResponseEntity.ok().body((List<Empresa>) empresaRepository.findAll());
    }

    @GetMapping("/{empresaId}")
    public ResponseEntity<?> getEmpresa(@PathVariable String empresaId) {
        int id = Integer.parseInt(empresaId);
        Optional<Empresa> empresa = empresaRepository.findById(id);
        if (empresa.isPresent()) {
            return ResponseEntity.ok().body(empresa.get());
        } else {
            return ResponseEntity.ok().body("La empresa pedida no está registrada.");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> createEmpresa(@RequestBody Empresa empresaReq) {
        try {

            if(empresaReq == null) {
                return ResponseEntity.badRequest().body("No ha proporcionado ninguna empresa");
            }

            if(empresaReq.getNombre() == null || empresaReq.getNombre().equalsIgnoreCase("") ||
                empresaReq.getEmail() == null || empresaReq.getEmail().equalsIgnoreCase("") ||
                empresaReq.getTelefono() == null || empresaReq.getTelefono().equalsIgnoreCase("") ||
                empresaReq.getDireccion() == null) {
                    return ResponseEntity.badRequest().body("Los atributos de la empresa proporcionada son incorrectos");
            }

            if(!empresaRepository.findByEmail(empresaReq.getEmail()).isEmpty()) {
                return ResponseEntity.badRequest().body("Ese email ya está en uso");
            }

            if(!empresaRepository.findByNombre(empresaReq.getNombre()).isEmpty()) {
                return ResponseEntity.badRequest().body("Ese nombre de usuario ya está en uso");
            }

            if(!empresaRepository.findByTelefono(empresaReq.getTelefono()).isEmpty()) {
                return ResponseEntity.badRequest().body("Ese teléfono de usuario ya está en uso");
            }

            Empresa newEmpresa = new Empresa();
            newEmpresa.setNombre(empresaReq.getNombre());
            newEmpresa.setEmail(empresaReq.getEmail());
            newEmpresa.setTelefono(empresaReq.getTelefono());

            Direccion direccionEmpresa = direccionService.getDireccion(empresaReq.getDireccion());
            if(direccionEmpresa == null) {
                // Direccion newDireccion = direccionService.createDireccion(empresaReq.getDireccion());
                newEmpresa.setDireccion(empresaReq.getDireccion());
            } else {
                newEmpresa.setDireccion(direccionEmpresa);
            }

            Empresa empresaCreada = empresaRepository.save(newEmpresa);

            return ResponseEntity.created(new URI("/easytrack/api/empresas/" + newEmpresa.getId())).body(empresaCreada);
        } catch (IllegalArgumentException iae) {
            return ResponseEntity.badRequest().body(iae.getMessage());
        } catch (URISyntaxException use) {
            return ResponseEntity.badRequest().body(use);
        }   
    }

    @PostMapping("/prueba")
    public ResponseEntity<?> createBadEmpresa(@RequestBody Empresa empresaReq) {
        return ResponseEntity.ok().body(empresaReq);
    }

    @PutMapping("/{empresaId}")
    public ResponseEntity<?> modifyEmpresa(@PathVariable String empresaId, @RequestBody Empresa empresaReq) {
        int idEmpresa = Integer.parseInt(empresaId);
        if(!empresaRepository.existsById(idEmpresa)) {
            return ResponseEntity.badRequest().body("La empresa que quiere modificar no está registrada.");
        }
        if(idEmpresa != empresaReq.getId()) {
            return ResponseEntity.badRequest().body("empresaId and empresaReq.getId() mismatch");
        }
        Empresa empresaModificada = empresaRepository.findById(idEmpresa).get();
        empresaModificada.setNombre(empresaReq.getNombre());
        empresaModificada.setEmail(empresaReq.getEmail());
        empresaModificada.setTelefono(empresaReq.getTelefono());
        empresaModificada.setDireccion(empresaReq.getDireccion());
        empresaRepository.save(empresaModificada);
        return ResponseEntity.ok().body(empresaModificada);
    }

    @DeleteMapping("/{empresaId}")
    public ResponseEntity<?> deleteEmpresa(@PathVariable String empresaId) {
        int idEmpresa = Integer.parseInt(empresaId);
        if(!empresaRepository.existsById(idEmpresa)) {
            return ResponseEntity.badRequest().body("Esa empresa ya no está registrada");
        }
        Empresa empresaEliminada = empresaRepository.findById(idEmpresa).get();
        empresaRepository.deleteById(idEmpresa);
        return ResponseEntity.ok().body(empresaEliminada);
    }
}
