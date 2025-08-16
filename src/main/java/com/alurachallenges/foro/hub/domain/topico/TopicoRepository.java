package com.alurachallenges.foro.hub.domain.topico;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TopicoRepository extends JpaRepository<Topico, Long> {

    Page<Topico> findAll(Pageable paginacion);

    Page<Topico> findAllByStatusTrue(Pageable paginacion);

    boolean existsByTituloAndMensaje(String titulo, String mensaje);

}
