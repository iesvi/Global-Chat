package com.iesvi.gestionCorreo.application;

import com.iesvi.gestionCorreo.application.dto.CorreoDTO;
import com.iesvi.gestionCorreo.domain.vo.CorreoVO;
import com.iesvi.gestionCorreo.domain.repos.CorreoRepository;
import com.iesvi.utils.Notification;
import com.iesvi.utils.CorreoConverterToDTO;
import com.iesvi.utils.CorreoConverterToVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CorreoServiceImpl implements CorreoService {

    //************************* NUEVO CODIGO PARA SSE***************************
    public final ApplicationEventPublisher eventPublisher;

    @Autowired
    private CorreoRepository correoRepository;

    @Autowired
    private CorreoConverterToDTO correoConverterToDTO;

    @Autowired
    private CorreoConverterToVO correoConverterToVO;

    //************************* NUEVO CODIGO PARA SSE***************************
    public CorreoServiceImpl(ApplicationEventPublisher eventPublisher) {
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<CorreoDTO> findAll() {
        return correoRepository.findAll()
                .stream()
                .map(correoConverterToDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public List<CorreoDTO> findByRemitente(String remitente) {
        return correoRepository.findAll()
                .stream()
                .filter(correoVO -> correoVO.getRemitente().contains(remitente))
                .map(correoConverterToDTO::convert)
                .collect(Collectors.toList());
    }

    @Override
    public CorreoDTO createCorreo(CorreoDTO correoDTO) {
        //************************* NUEVO CODIGO PARA SSE***************************
        CorreoVO correoVO = correoConverterToVO.convert(correoDTO);

        try {
            publishJobNotifications();
            return correoConverterToDTO.convert(correoRepository.insert(correoVO));
        } catch (Exception e) {
            return correoConverterToDTO.convert(correoRepository.insert(correoVO));
        }

    }

    //************************* NUEVO CODIGO PARA SSE***************************
    public void publishJobNotifications() throws InterruptedException {

        Notification nStarted = new Notification("¡Se ha enviado con exito el correo! Fecha: ", new Date());
        this.eventPublisher.publishEvent(nStarted);

    }

    @Override
    public boolean deleteCorreo(String id) {
        try {
            correoRepository.deleteById(id);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @Override
    public boolean deleteAllCorreos() {
        try {
            correoRepository.deleteAll();
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

}
