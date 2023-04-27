package es.upm.dit.isst.backend.service;

import es.upm.dit.isst.backend.model.Empresa;

public interface EmpresaService {
    public Empresa getEmpresaByNombre(String nombre);
    public Empresa getEmpresaByEmail(String email);
    public Empresa getEmpresaByTelefono(String telefono);
    public Empresa createEmpresa(Empresa empresaReq);
}
