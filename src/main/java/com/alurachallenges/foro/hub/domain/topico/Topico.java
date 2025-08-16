package com.alurachallenges.foro.hub.domain.topico;

import com.alurachallenges.foro.hub.domain.respuesta.Respuesta;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "topico")
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    private Boolean status;

    @Column(name = "autor_id")
    private Long autorId;

    @Column(name = "curso_id")
    private Long cursoId;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Respuesta> respuestas = new HashSet<>();

    public Topico(DatosPublicarTopico datos) {
        this.id = null;
        this.titulo = datos.titulo();
        this.mensaje = datos.mensaje();
        this.fechaCreacion = LocalDateTime.now();
        this.status = true;
        this.autorId = datos.autorId();
        this.cursoId = datos.cursoId();
    }

    public void editarTopico(@Valid DatosActualizacionTopico datos) {
        if(datos.titulo() != null){
            this.titulo = datos.titulo();
        }
        if(datos.mensaje() != null){
            this.mensaje = datos.mensaje();
        }
        if(datos.cursoId() != null) {
            this.cursoId = datos.cursoId();
        }
    }

    public void ocultar() {
        this.status = !this.status;
    }

}