package com.iesvi.utils;

import com.iesvi.gestionUsuario.application.dto.UsuarioDTO;
import com.iesvi.gestionUsuario.domain.vo.UsuarioVO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UsuarioConverterToDTO implements Converter<UsuarioVO, UsuarioDTO> {

    @Override
    public UsuarioDTO convert(UsuarioVO usuarioVO) {
        return UsuarioDTO.builder()
                .id(usuarioVO.getId())
                .nombre(usuarioVO.getNombre())
                .usuario(usuarioVO.getUsuario())
                .telefono(usuarioVO.getTelefono())
                .contraseña(usuarioVO.getContraseña())
                .mensajes(usuarioVO.getMensajes())
                .build();
    }

}