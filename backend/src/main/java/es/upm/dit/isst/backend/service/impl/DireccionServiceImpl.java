package es.upm.dit.isst.backend.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.upm.dit.isst.backend.model.Direccion;
import es.upm.dit.isst.backend.repository.DireccionRepository;
import es.upm.dit.isst.backend.service.DireccionService;

@Service
public class DireccionServiceImpl implements DireccionService{

    @Autowired
    DireccionRepository direccionRepository;

    @Override
    public Direccion getDireccion(Direccion direccionReq) {
        List<Direccion> direcciones = (List<Direccion>) direccionRepository.findAll();
        for (Direccion direccion : direcciones) {
            if(direccion.equals(direccionReq)) {
                return direccion;
            }
        }
        return null;
    }

    @Override
    public Direccion createDireccion(Direccion direccionReq) {
        Direccion newDireccion = new Direccion();
        newDireccion.setLatitud(direccionReq.getLatitud());
        newDireccion.setLongitud(direccionReq.getLongitud());
        newDireccion.setProvincia(direccionReq.getProvincia());
        newDireccion.setCiudad(direccionReq.getCiudad());
        newDireccion.setDomicilio(direccionReq.getDomicilio());
        newDireccion.setPostal_code(direccionReq.getPostal_code());
        Direccion direccionCreada = direccionRepository.save(newDireccion);
        return direccionCreada;
    }

    @Override
    public String checkDireccion(Direccion direccionReq) {
        if(direccionReq == null) {
            return "No se ha proporcionado ninguna dirección.";
        }

        if(direccionReq.getLatitud() == 0 || direccionReq.getLongitud() == 0 ||
            direccionReq.getDomicilio() == null || direccionReq.getDomicilio().isEmpty()) {
            return "Faltan argumentos, debe proporcionar: latitud (float), longitud (float) y domicilio (string)";
        }
        
        return null;
    }
    
}
