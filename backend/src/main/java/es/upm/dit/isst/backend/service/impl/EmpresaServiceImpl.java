package es.upm.dit.isst.backend.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import es.upm.dit.isst.backend.model.Direccion;
import es.upm.dit.isst.backend.model.Empresa;
import es.upm.dit.isst.backend.repository.DireccionRepository;
import es.upm.dit.isst.backend.repository.EmpresaRepository;
import es.upm.dit.isst.backend.service.DireccionService;
import es.upm.dit.isst.backend.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{

    DireccionRepository direccionRepository;

    DireccionService direccionService;

    EmpresaRepository empresaRepository;

    @Override
    public Empresa getEmpresaByEmail(String email) {
        return empresaRepository.findByEmail(email).get(0);
    }

    @Override
    public Empresa getEmpresaByNombre(String nombre) {
        return empresaRepository.findByNombre(nombre).get(0);
    }

    @Override
    public Empresa getEmpresaByTelefono(String telefono) {
        return empresaRepository.findByTelefono(telefono).get(0);
    }

    @Override
    public Empresa createEmpresa(Empresa empresaReq) {
        Direccion direccionExistente = direccionService.getDireccion(empresaReq.getDireccion());
        return null;
    }
    
}
