package com.iesvi.gestionUsuario.infra.controller;

import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Samuel on 29/04/2021.
 * @proyect TFG
 */
@RequestMapping(EndPointUris.API + EndPointUris.V1 + EndPointUris.USUARIO)
public interface UsuarioAPI {

    @GetMapping(EndPointUris.FINDALL)
    ResponseEntity<List<UsuarioDTO>> findAll();

    @GetMapping(EndPointUris.FINDBYUSUARIO + EndPointUris.USER)
    ResponseEntity<List<UsuarioDTO>> findByUsuario(@PathVariable final String usuario);

    @GetMapping(EndPointUris.FINDBYTELEFONO + EndPointUris.TELEFONO)
    ResponseEntity<List<UsuarioDTO>> findByTelefono(@PathVariable final String telefono);

    @PostMapping(EndPointUris.CREATEUSUARIO)
    ResponseEntity<UsuarioDTO> createUsuario(@RequestBody final UsuarioDTO usuarioDTO);

    @PutMapping(EndPointUris.UPDATEUSUARIO + EndPointUris.ID)
    ResponseEntity<UsuarioDTO> updateUsuario(@PathVariable final String id, @RequestBody final UsuarioDTO usuarioDTO);

    @DeleteMapping(EndPointUris.DELETEUSUARIO + EndPointUris.ID)
    ResponseEntity<Boolean> deleteUsuario(@PathVariable final String id);

}
