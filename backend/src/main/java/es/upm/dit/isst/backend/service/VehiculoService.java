package es.upm.dit.isst.backend.service;

import es.upm.dit.isst.backend.model.Vehiculo;

public interface VehiculoService {
    public Vehiculo createVehiculo(Vehiculo vehiculoReq);
    public Vehiculo getVehiculoByMatricula(String matricula);
}
