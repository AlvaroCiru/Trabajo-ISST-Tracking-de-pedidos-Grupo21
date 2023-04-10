package es.upm.dit.isst.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.upm.dit.isst.backend.model.Comprador;
import es.upm.dit.isst.backend.model.Empresa;
import es.upm.dit.isst.backend.model.Pedido;
import es.upm.dit.isst.backend.model.Usuario;
import es.upm.dit.isst.backend.repository.CompradorRepository;
import es.upm.dit.isst.backend.repository.EmpresaRepository;
import es.upm.dit.isst.backend.repository.PedidoRepository;

@RestController
@RequestMapping("/")
public class PedidoController {

    @Autowired
    PedidoRepository pedidoRepository;

    @Autowired
    CompradorRepository compradorRepository;

    @Autowired
    EmpresaRepository empresaRepository;

    @GetMapping()
    public List<Pedido> readAll() {
        return (List<Pedido>) pedidoRepository.findAll();
    }

    @GetMapping("comprador/{userId}")
    public List<Pedido> getPedidosByComprador(@PathVariable int compradorId) {
        List<Comprador> compradores = compradorRepository.findById(compradorId);
        Comprador comprador = compradores.get(0);
        List<Pedido> listaPedidos = pedidoRepository.findByComprador(comprador);
        return listaPedidos;
    }

}
