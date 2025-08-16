package com.alurachallenges.foro.hub.controller;

import com.alurachallenges.foro.hub.domain.curso.Curso;
import com.alurachallenges.foro.hub.domain.curso.CursoRepository;
import com.alurachallenges.foro.hub.domain.perfil.Perfil;
import com.alurachallenges.foro.hub.domain.perfil.PerfilRepository;
import com.alurachallenges.foro.hub.domain.topico.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository topicoRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private PerfilRepository perfilRepository;


    @Transactional
    @PostMapping
    public ResponseEntity<DetallesTopico> registrarTopico(
            @RequestBody @Valid DatosPublicarTopico datos,
            UriComponentsBuilder uriComponentsBuilder) {

        // Crear y guardar el tópico
        Topico topico = new Topico(datos);
        topicoRepository.save(topico);

        // Traer entidades completas
        Optional<Perfil> autor = perfilRepository.findById(topico.getAutorId());
        Optional<Curso> curso = cursoRepository.findById(topico.getCursoId());

        // Construir DTO
        DetallesTopico detalles = new DetallesTopico(topico, autor.orElse(null), curso.orElse(null));

        // Construir URI de creación
        var uri = uriComponentsBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();

        return ResponseEntity.created(uri).body(detalles);
    }

    @GetMapping
    public ResponseEntity<Page<ListaTopico>> listar(@PageableDefault(size = 5, sort = {"fechaCreacion"}) Pageable paginacion){

        var page = topicoRepository.findAllByStatusTrue(paginacion)
                .map(topico -> new ListaTopico(topico, cursoRepository));
        return ResponseEntity.ok(page);

    }

    @GetMapping("/{id}")
    public ResponseEntity<DetallesTopico> detalleTopico(@PathVariable Long id) {
        Topico topico = topicoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tópico no encontrado"));

        DetallesTopico detalles = perfilRepository.findById(topico.getAutorId())
                .map(autor -> cursoRepository.findById(topico.getCursoId())
                        .map(curso -> new DetallesTopico(topico, autor, curso))
                        .orElseThrow(() -> new RuntimeException("Curso no encontrado"))
                )
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        return ResponseEntity.ok(detalles);
    }

    @Transactional
    @PutMapping
    public ResponseEntity actualizar (@RequestBody @Valid DatosActualizacionTopico datos){
        var topico = topicoRepository.getReferenceById(datos.id());
        topico.editarTopico(datos);

        DetallesTopico detalles = perfilRepository.findById(topico.getAutorId())
                .map(autor -> cursoRepository.findById(topico.getCursoId())
                        .map(curso -> new DetallesTopico(topico, autor, curso))
                        .orElseThrow(() -> new RuntimeException("Curso no encontrado"))
                )
                .orElseThrow(() -> new RuntimeException("Autor no encontrado"));

        return ResponseEntity.ok(detalles);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity ocultar (@PathVariable Long id){

        //para eliminar de la base de datos
        //repository.deleteById(id);

        var topico = topicoRepository.getReferenceById(id);
        topico.ocultar();

        return ResponseEntity.noContent().build();
    }

}
