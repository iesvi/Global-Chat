package com.iesvi.gestionUsuario.application.dto;

import com.iesvi.gestionCorreo.application.dto.CorreoDTO;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Builder
@ToString
public class UsuarioDTO {

    private String id;
    private String nombre;
    private String usuario;
    private String telefono;
    private String contraseña;
    private List<CorreoDTO> mensajes;

}
