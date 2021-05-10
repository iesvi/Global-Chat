package com.iesvi.gestionUsuario.domain.vo;

import com.iesvi.gestionCorreo.application.dto.CorreoDTO;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
@Document(collection = "Usuario")
public class UsuarioVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String nombre;
    private String usuario;
    private String telefono;
    private String contraseña;
    private List<CorreoDTO> mensajes;

}

