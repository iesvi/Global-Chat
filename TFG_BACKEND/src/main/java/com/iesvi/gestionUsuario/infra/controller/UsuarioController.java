package com.iesvi.gestionUsuario.infra.controller;

import com.iesvi.gestionCorreo.application.dto.CorreoDTO;
import com.iesvi.gestionCorreo.infra.controller.EndPointUris;
import com.iesvi.gestionUsuario.application.UsuarioService;
import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class UsuarioController implements UsuarioAPI {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    public ResponseEntity<List<UsuarioDTO>> findAll() {
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @Override
    public ResponseEntity<List<UsuarioDTO>> findByUsuario(String usuario) {
        return ResponseEntity.ok(usuarioService.findByUsuario(usuario));
    }

    @Override
    public ResponseEntity<List<UsuarioDTO>> findByTelefono(String telefono) {
        return ResponseEntity.ok(usuarioService.findByTelefono(telefono));
    }

    @Override
    public ResponseEntity<UsuarioDTO> createUsuario(UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.createUsuario(usuarioDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UsuarioDTO> updateUsuario(String id, UsuarioDTO usuarioDTO) {
        return new ResponseEntity<>(usuarioService.updateUsuario(id, usuarioDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Boolean> deleteUsuario(String id) {
        return usuarioService.deleteUsuario(id)
                ? ResponseEntity.ok(true)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

}
