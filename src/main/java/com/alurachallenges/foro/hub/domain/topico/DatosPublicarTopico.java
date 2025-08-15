package com.alurachallenges.foro.hub.domain.topico;

import java.time.LocalDateTime;

public record DatosPublicarTopico(
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Long autorId,
        Long cursoId
) {
}
