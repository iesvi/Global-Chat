package com.iesvi.gestionCorreo.infra.controller;

import com.iesvi.gestionCorreo.application.dto.CorreoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

@RequestMapping(EndPointUris.API + EndPointUris.V1 + EndPointUris.CORREO)
public interface CorreoAPI {

    @GetMapping(EndPointUris.FINDALL)
    ResponseEntity<List<CorreoDTO>> findAll();

    @GetMapping(EndPointUris.FINDBYREMITENTE + EndPointUris.REMITENTE)
    ResponseEntity<List<CorreoDTO>> findByRemitente(@PathVariable final String remitente);

    @PostMapping(EndPointUris.CREATECORREO)
    ResponseEntity<CorreoDTO> createCorreo(@RequestBody final CorreoDTO correoDTO);

    @DeleteMapping(EndPointUris.DELETECORREO + EndPointUris.ID)
    ResponseEntity<Boolean> deleteCorreo(@PathVariable final String id);

    @DeleteMapping(EndPointUris.DELETEALLCORREOS)
    ResponseEntity<Boolean> deleteAllCorreos();

    //************************* NUEVO CODIGO PARA SSE***************************
    @GetMapping(EndPointUris.NOTIFICATIONS)
    public SseEmitter getNewNotification();

}