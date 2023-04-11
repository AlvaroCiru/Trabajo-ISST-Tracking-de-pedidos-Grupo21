package es.upm.dit.isst.backend.controller;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
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
            return ResponseEntity.ok().body("La empresa pedida no existe");
        }
        
    }

    @PostMapping("/empresas")
    public ResponseEntity<?> createEmpresa(@RequestBody Empresa empresaReq) {
        return null;
    }

    @PutMapping("/empresas/{empresaId}")
    public ResponseEntity<?> modifyEmpresa(@PathVariable String empresaId, @RequestBody Empresa empresaReq) {
        return null;
    }

    @DeleteMapping("/empresas/{empresaId}")
    public ResponseEntity<?> deleteEmpresa(@PathVariable String empresaId) {
        return null;
    }
}
