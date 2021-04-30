package com.iesvi.gestionCorreo.domain.vo;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "Correo")
public class CorreoVO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String remitente;
    private String destinatario;
    private String asunto;
    private String mensaje;

}
