package com.alurachallenges.foro.hub.domain.topico;

import com.alurachallenges.foro.hub.domain.curso.Curso;
import jakarta.validation.constraints.NotNull;

public record DatosActualizacionTopico(
        @NotNull Long id,
        String titulo,
        String mensaje,
        Long cursoId
) {
}
