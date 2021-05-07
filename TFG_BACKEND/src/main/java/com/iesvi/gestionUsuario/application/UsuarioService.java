package com.iesvi.gestionUsuario.application;

import com.iesvi.gestionCorreo.application.dto.CorreoDTO;
import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;

import java.util.List;

/**
 * @author Samuel on 28/04/2021.
 * @proyect TFG
 */
public interface UsuarioService {

    List<UsuarioDTO> findAll();

    List<UsuarioDTO> findByUsuario(final String usuario);

    List<UsuarioDTO> findByTelefono(final String telefono);

    UsuarioDTO createUsuario(final UsuarioDTO usuarioDTO);

    UsuarioDTO updateUsuario(final String id, final UsuarioDTO usuarioDTO);

    boolean deleteUsuario(final String id);

}
