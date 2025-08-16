package com.alurachallenges.foro.hub.controller;

import com.alurachallenges.foro.hub.domain.curso.CursoRepository;
import com.alurachallenges.foro.hub.domain.topico.*;
import com.alurachallenges.foro.hub.domain.ValidarDuplicadoException;
import com.alurachallenges.foro.hub.domain.usuario.UsuarioDTO;
import com.alurachallenges.foro.hub.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @PostMapping
    public ResponseEntity<DetallesTopico> registrarTopico(
            @RequestBody @Valid DatosPublicarTopico datos,
            UriComponentsBuilder uriComponentsBuilder) {

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())) {
            throw new ValidarDuplicadoException("Ya existe un tópico con el mismo título y mensaje");
        }

        Topico topico = new Topico(datos);
        topicoRepository.save(topico);

        var usuario = usuarioRepository.findById(topico.getAutorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
        var autor = new UsuarioDTO(usuario);

        var curso = cursoRepository.findById(topico.getCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));

        var detalles = new DetallesTopico(topico, autor, curso);
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(detalles);
    }

    @GetMapping
    public ResponseEntity<Page<ListaTopico>> listar(
            @PageableDefault(size = 10, sort = {"fechaCreacion"}, direction = Sort.Direction.DESC) Pageable paginacion) {

        var page = topicoRepository.findAllByStatusTrue(paginacion)
                .map(topico -> new ListaTopico(topico, cursoRepository));

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesTopico> detalleTopico(@PathVariable Long id) {
        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        var usuario = usuarioRepository.findById(topico.getAutorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
        var autor = new UsuarioDTO(usuario);

        var curso = cursoRepository.findById(topico.getCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));

        var detalles = new DetallesTopico(topico, autor, curso);
        return ResponseEntity.ok(detalles);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<DetallesTopico> actualizar(
            @PathVariable Long id,
            @RequestBody @Valid DatosActualizacionTopico datos) {

        var topico = topicoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tópico no encontrado"));

        if (topicoRepository.existsByTituloAndMensaje(datos.titulo(), datos.mensaje())
                && (!topico.getTitulo().equals(datos.titulo()) || !topico.getMensaje().equals(datos.mensaje()))) {
            throw new ValidarDuplicadoException("Ya existe un tópico con el mismo título y mensaje");
        }

        topico.editarTopico(datos);

        var usuario = usuarioRepository.findById(topico.getAutorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor no encontrado"));
        var autor = new UsuarioDTO(usuario);

        var curso = cursoRepository.findById(topico.getCursoId())
                .orElseThrow(() -> new EntityNotFoundException("Curso no encontrado"));

        var detalles = new DetallesTopico(topico, autor, curso);
        return ResponseEntity.ok(detalles);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity borrar(@PathVariable Long id) {

        topicoRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}