package com.iesvi.gestionCorreo.application;

import com.iesvi.gestionCorreo.application.dto.CorreoDTO;

import java.util.List;

public interface CorreoService {

    List<CorreoDTO> findAll();

    List<CorreoDTO> findByRemitente(final String remitente);

    CorreoDTO createCorreo(final CorreoDTO correoDTO);

    boolean deleteCorreo(final String id);

    boolean deleteAllCorreos();

}
