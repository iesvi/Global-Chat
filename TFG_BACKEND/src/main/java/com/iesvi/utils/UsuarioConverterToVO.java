package com.iesvi.utils;

import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.gestionUsuario.domain.vo.UsuarioVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverterToVO implements Converter<UsuarioDTO, UsuarioVO> {

    @Override
    public UsuarioVO convert(UsuarioDTO usuarioDTO) {
        return UsuarioVO.builder()
                .id(usuarioDTO.getId())
                .nombre(usuarioDTO.getNombre())
                .usuario(usuarioDTO.getUsuario())
                .telefono(usuarioDTO.getTelefono())
                .contraseña(usuarioDTO.getContraseña())
                .mensajes(usuarioDTO.getMensajes())
                .build();
    }

}