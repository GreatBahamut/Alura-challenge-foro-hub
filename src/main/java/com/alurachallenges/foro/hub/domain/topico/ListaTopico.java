package com.alurachallenges.foro.hub.domain.topico;

import com.alurachallenges.foro.hub.domain.curso.Curso;
import com.alurachallenges.foro.hub.domain.curso.CursoRepository;
import com.alurachallenges.foro.hub.domain.respuesta.Respuesta;

import java.time.LocalDateTime;
import java.util.Set;

public record ListaTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fechaCreacion,
        Set<Respuesta> respuestas,
        Boolean status,
        String curso
) {



    public ListaTopico(Topico topico, CursoRepository cursoRepository) {


        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensaje(),
                topico.getFechaCreacion(),
                topico.getRespuestas(),
                topico.getStatus(),
                cursoRepository.findById(topico.getCursoId())
                        .map(Curso::getNombre)
                        .orElse("Curso desconocido")
        );
    }
}
