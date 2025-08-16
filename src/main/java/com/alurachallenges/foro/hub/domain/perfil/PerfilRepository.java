package com.alurachallenges.foro.hub.domain.perfil;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    default Perfil buscarPorId(Long id) {
        return findById(id).orElseThrow(() -> new RuntimeException("Perfil no encontrado"));
    }
}
