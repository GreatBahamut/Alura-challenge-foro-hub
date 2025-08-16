package com.alurachallenges.foro.hub.domain.respuesta;

import java.time.LocalDateTime;

public record DetalleRespuesta(
        Long id,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean solucion,
        Long autorId
) {
    public DetalleRespuesta(Respuesta respuesta) {
        this(
                respuesta.getId(),
                respuesta.getMensaje(),
                respuesta.getFechaCreacion(),
                respuesta.getSolucion(),
                respuesta.getAutorId()
        );
    }
}
