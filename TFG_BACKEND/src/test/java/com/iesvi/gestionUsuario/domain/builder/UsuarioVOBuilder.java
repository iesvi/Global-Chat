package com.iesvi.gestionUsuario.domain.builder;

import com.iesvi.gestionCorreo.application.dto.CorreoDTO;
import com.iesvi.gestionUsuario.domain.vo.UsuarioVO;
import com.iesvi.shared.domain.VOBuilder;
import io.beanmother.core.ObjectMother;

import java.util.List;

public class UsuarioVOBuilder extends VOBuilder {

    String id;
    String nombre;
    String usuario;
    String telefono;
    String contraseña;
    List<CorreoDTO> mensajes;

    public UsuarioVOBuilder() {
    }

    public UsuarioVO build() {
        ObjectMother om = ObjectMother.getInstance();
        UsuarioVO mother = om.bear("UsuarioVO", UsuarioVO.class);

        return new UsuarioVO(
                id != null ? id : mother.getId(),
                nombre != null ? nombre : mother.getNombre(),
                usuario != null ? usuario : mother.getUsuario(),
                telefono != null ? telefono : mother.getTelefono(),
                contraseña != null ? contraseña : mother.getContraseña(),
                mensajes != null ? mensajes : mother.getMensajes()
        );
    }

}
