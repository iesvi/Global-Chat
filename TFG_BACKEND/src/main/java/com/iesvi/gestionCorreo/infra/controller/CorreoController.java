package com.iesvi.gestionCorreo.infra.controller;

import com.iesvi.gestionCorreo.application.dto.CorreoDTO;
import com.iesvi.gestionCorreo.domain.repos.CorreoRepository;
import com.iesvi.utils.Notification;
import com.iesvi.gestionCorreo.application.CorreoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RestController
@CrossOrigin
public class CorreoController implements CorreoAPI {

    //************************* NUEVO CODIGO PARA SSE***************************
    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    @Autowired
    private CorreoService correoService;

    @Override
    public ResponseEntity<List<CorreoDTO>> findAll() {
        return ResponseEntity.ok(correoService.findAll());
    }

    @Override
    public ResponseEntity<List<CorreoDTO>> findByRemitente(String remitente) {
        return ResponseEntity.ok(correoService.findByRemitente(remitente));
    }

    @Override
    public ResponseEntity<CorreoDTO> createCorreo(CorreoDTO correoDTO) {
        return new ResponseEntity<>(correoService.createCorreo(correoDTO), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Boolean> deleteCorreo(String id) {
        return correoService.deleteCorreo(id)
                ? ResponseEntity.ok(true)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<Boolean> deleteAllCorreos() {
        return correoService.deleteAllCorreos()
                ? ResponseEntity.ok(true)
                : new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
    }

    //************************* NUEVO CODIGO PARA SSE***************************
    @EventListener
    public void onNotification(Notification notification) {
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {

                emitter.send(notification);

            } catch (Exception e) {

                deadEmitters.add(emitter);
            }
        });
        this.emitters.remove(deadEmitters);
    }

    //************************* NUEVO CODIGO PARA SSE***************************
    public SseEmitter getNewNotification() {
        SseEmitter emitter = new SseEmitter();
        this.emitters.add(emitter);

        emitter.onCompletion(() -> this.emitters.remove(emitter));
        emitter.onTimeout(() -> {
            emitter.complete();
            this.emitters.remove(emitter);
        });

        return emitter;
    }


}
