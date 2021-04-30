package com.iesvi.utils;

import com.iesvi.gestionCorreo.application.dto.CorreoDTO;
import com.iesvi.gestionCorreo.domain.vo.CorreoVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CorreoConverterToVO implements Converter<CorreoDTO, CorreoVO> {

    @Override
    public CorreoVO convert(CorreoDTO correoDTO) {
        return CorreoVO.builder()
                .id(correoDTO.getId())
                .remitente(correoDTO.getRemitente())
                .destinatario(correoDTO.getDestinatario())
                .asunto(correoDTO.getAsunto())
                .mensaje(correoDTO.getMensaje())
                .build();
    }
}
