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
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.backend.model.Empresa;
import es.upm.dit.isst.backend.repository.EmpresaRepository;


@RestController
public class EmpresaController {

    @Autowired
    EmpresaRepository empresaRepository;
    
    @GetMapping("/empresas")
    public ResponseEntity<?> getAllEmpresas() {
        return ResponseEntity.ok().body((List<Empresa>) empresaRepository.findAll());
    }

    @GetMapping("/empresas/{empresaId}")
    public ResponseEntity<?> getEmpresa(@PathVariable String empresaId) {
        int id = Integer.parseInt(empresaId);
        Optional<Empresa> empresa = empresaRepository.findById(id);
        if (empresa.isPresent()) {
            return ResponseEntity.ok().body(empresa.get());
        } else {
            return ResponseEntity.ok().body("La empresa pedida no está registrada.");
        }
    }

    @PostMapping("/empresas")
    public ResponseEntity<?> createEmpresa(@RequestBody Empresa empresaReq) {
        try {
            if(empresaReq == null) {
                return ResponseEntity.badRequest().body("No ha proporcionado la empresa correctamente.");
            }
            if(empresaRepository.existsById(empresaReq.getId()))
                return ResponseEntity.ok().body("La empresa proporcionada ya está registrada.");
            empresaRepository.save(empresaReq);
            return ResponseEntity.created(new URI("/empresas/" + empresaReq.getId())).body(empresaReq);
            } catch (IllegalArgumentException iae) {
                return ResponseEntity.badRequest().body(iae);
            } catch (URISyntaxException use) {
                return ResponseEntity.badRequest().body(use);
            }
        
    }

    @PutMapping("/empresas/{empresaId}")
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

    @DeleteMapping("/empresas/{empresaId}")
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
