package es.upm.dit.isst.backend.service.impl;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.upm.dit.isst.backend.model.Traza;
import es.upm.dit.isst.backend.model.Vehiculo;
import es.upm.dit.isst.backend.model.Pedido;
import es.upm.dit.isst.backend.repository.PedidoRepository;
import es.upm.dit.isst.backend.repository.TrazaRepository;
import es.upm.dit.isst.backend.repository.VehiculoRepository;
import es.upm.dit.isst.backend.service.TrazaService;
import es.upm.dit.isst.backend.service.VehiculoService;

@Service
public class TrazaServiceImpl implements TrazaService{

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    VehiculoRepository vehiculoRepository;

    @Autowired
    VehiculoService vehiculoService;

    @Autowired
    TrazaRepository trazaRepository;

    @Override
    public Traza createTraza(Traza trazaReq) {
        Traza newTraza = new Traza();
        newTraza.setLatitud(trazaReq.getLatitud());
        newTraza.setLongitud(trazaReq.getLongitud());

        if(trazaReq.getFecha_creacion() == null) {
            newTraza.setFecha_creacion(LocalDate.now());
        } else {
            newTraza.setFecha_creacion(trazaReq.getFecha_creacion());
        }

        if(trazaReq.getHora_creacion() == null) {
            newTraza.setHora_creacion(LocalTime.now());
        } else {
            newTraza.setHora_creacion(trazaReq.getHora_creacion());
        }

        if(!pedidoRepository.findById(trazaReq.getPedido().getCodigo()).isPresent()) {
            throw new IllegalArgumentException("No existe ningún pedido con el código proporcionado");
        } else {
            newTraza.setPedido(pedidoRepository.findById(trazaReq.getPedido().getCodigo()).get());
        }

        if(vehiculoService.getVehiculoByMatricula(trazaReq.getVehiculo().getMatricula()) == null) {
            Vehiculo newVehiculo = vehiculoService.createVehiculo(trazaReq.getVehiculo());
            newTraza.setVehiculo(newVehiculo);
        } else {
            newTraza.setVehiculo(vehiculoService.getVehiculoByMatricula(trazaReq.getVehiculo().getMatricula()));
        }
        
        Traza trazaCreada = trazaRepository.save(newTraza);
        return trazaCreada;
    }
    
}
