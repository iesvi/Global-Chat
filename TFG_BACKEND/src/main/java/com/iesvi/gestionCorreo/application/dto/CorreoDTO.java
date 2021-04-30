package com.iesvi.gestionCorreo.application.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CorreoDTO {

    private String id;

    private String remitente;
    private String destinatario;
    private String asunto;
    private String mensaje;

}
