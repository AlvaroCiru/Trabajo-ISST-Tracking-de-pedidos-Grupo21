package es.upm.dit.isst.backend.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.upm.dit.isst.backend.model.Vehiculo;
import es.upm.dit.isst.backend.repository.VehiculoRepository;
import es.upm.dit.isst.backend.service.VehiculoService;

@Service
public class VehiculoServiceImpl implements VehiculoService{

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Override
    public Vehiculo createVehiculo(Vehiculo vehiculoReq) {
        Vehiculo newVehiculo = new Vehiculo();
        newVehiculo.setMatricula(vehiculoReq.getMatricula());
        newVehiculo.setModelo(vehiculoReq.getModelo());
        newVehiculo.setTelefono(vehiculoReq.getTelefono());
        Vehiculo vehiculoCreado = vehiculoRepository.save(newVehiculo);
        return vehiculoCreado;
    }

    @Override
    public Vehiculo getVehiculoByMatricula(String matricula) {
        return vehiculoRepository.findByMatricula(matricula).get(0);
    }
    
}
