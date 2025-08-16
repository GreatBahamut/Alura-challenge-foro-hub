package com.alurachallenges.foro.hub.domain.topico;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosPublicarTopico(
        @NotBlank(message = "El título no puede estar vacío")
        String titulo,
        @NotBlank(message = "El mensaje no puede estar vacío")
        String mensaje,
        LocalDateTime fechaCreacion,
        @NotNull(message = "El autor es obligatorio")
        Long autorId,
        @NotNull(message = "El curso es obligatorio")
        Long cursoId
) {
}
