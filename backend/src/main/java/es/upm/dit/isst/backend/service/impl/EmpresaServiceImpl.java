package es.upm.dit.isst.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.upm.dit.isst.backend.model.Direccion;
import es.upm.dit.isst.backend.model.Empresa;
import es.upm.dit.isst.backend.repository.DireccionRepository;
import es.upm.dit.isst.backend.repository.EmpresaRepository;
import es.upm.dit.isst.backend.service.DireccionService;
import es.upm.dit.isst.backend.service.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService{

    @Autowired
    DireccionRepository direccionRepository;

    @Autowired
    DireccionService direccionService;

    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public Empresa getEmpresa(Empresa empresareq) {
        List<Empresa> empresas = (List<Empresa>) empresaRepository.findAll();
        for (Empresa empresa : empresas) {
            if(empresa.equals(empresareq)) {
                return empresa;
            }
        }
        return null;
    }

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
        if(!empresaRepository.findByEmail(empresaReq.getEmail()).isEmpty()) {
            throw new IllegalArgumentException("Ya hay una empresa con ese Email");
        }

        if(!empresaRepository.findByNombre(empresaReq.getNombre()).isEmpty()) {
            throw new IllegalArgumentException("Ya hay una empresa con ese Nombre");
        }

        if(!empresaRepository.findByTelefono(empresaReq.getTelefono()).isEmpty()) {
            throw new IllegalArgumentException("Ya hay una empresa con ese Teléfono");
        }

        Empresa newEmpresa = new Empresa();
        newEmpresa.setNombre(empresaReq.getNombre());
        newEmpresa.setEmail(empresaReq.getEmail());
        newEmpresa.setTelefono(empresaReq.getTelefono());

        Direccion direccionEmpresa = direccionService.getDireccion(empresaReq.getDireccion());
        if(direccionEmpresa == null) {
            Direccion newDireccion = direccionService.createDireccion(empresaReq.getDireccion());
            newEmpresa.setDireccion(newDireccion);
        } else {
            newEmpresa.setDireccion(direccionEmpresa);
        }

        Empresa empresaCreada = empresaRepository.save(newEmpresa);
        return empresaCreada;
    }
    
}
