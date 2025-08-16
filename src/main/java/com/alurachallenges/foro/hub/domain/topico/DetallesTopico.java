package com.alurachallenges.foro.hub.domain.topico;

import com.alurachallenges.foro.hub.domain.curso.Curso;
import com.alurachallenges.foro.hub.domain.perfil.Perfil;
import com.alurachallenges.foro.hub.domain.respuesta.DetalleRespuesta;

import java.time.LocalDateTime;
import java.util.List;

public record DetallesTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Boolean status,
        Perfil autor,
        Curso curso,
        List<DetalleRespuesta> respuestas
) {
    public DetallesTopico(Topico topico, Perfil autor, Curso curso) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getStatus(),
                autor,
                curso,
                topico.getRespuestas().stream()
                        .map(DetalleRespuesta::new)
                        .toList()
        );
    }
}
