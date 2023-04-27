package es.upm.dit.isst.backend.service;

import es.upm.dit.isst.backend.model.Direccion;

public interface DireccionService {
    public Direccion getDireccion(Direccion direccion);
    public Direccion createDireccion(Direccion direccion);
    public void checkDireccion(Direccion direccion);
}
