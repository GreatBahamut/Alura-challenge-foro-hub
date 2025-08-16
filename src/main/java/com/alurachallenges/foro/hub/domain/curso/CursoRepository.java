package com.alurachallenges.foro.hub.domain.curso;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoRepository extends JpaRepository<Curso, Long> {
    default Curso buscarPorId(Long id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Curso no encontrado"));
    }
}
