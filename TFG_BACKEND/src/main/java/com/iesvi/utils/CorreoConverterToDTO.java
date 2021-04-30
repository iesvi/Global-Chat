package com.iesvi.utils;


import com.iesvi.gestionCorreo.application.dto.CorreoDTO;
import com.iesvi.gestionCorreo.domain.vo.CorreoVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class CorreoConverterToDTO implements Converter<CorreoVO, CorreoDTO> {

    @Override
    public CorreoDTO convert(CorreoVO correoVO) {
        return CorreoDTO.builder()
                .id(correoVO.getId())
                .remitente(correoVO.getRemitente())
                .destinatario(correoVO.getDestinatario())
                .asunto(correoVO.getAsunto())
                .mensaje(correoVO.getMensaje())
                .build();
    }
}
