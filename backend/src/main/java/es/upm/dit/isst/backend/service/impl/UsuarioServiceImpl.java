package es.upm.dit.isst.backend.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.upm.dit.isst.backend.model.Usuario;
import es.upm.dit.isst.backend.repository.UsuarioRepository;
import es.upm.dit.isst.backend.service.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public Usuario login(Usuario usuarioReq) {
        List<Usuario> usuarios = (List<Usuario>) usuarioRepository.findAll();
        for(Usuario usuario : usuarios) {
            if(usuario.getNombre().equals(usuarioReq.getNombre()) && usuario.getContrasena().equals(usuarioReq.getContrasena())) {
                return usuario;
            }
        }
        return null;
    }
    
}
