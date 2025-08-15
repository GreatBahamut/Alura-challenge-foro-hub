package com.alurachallenges.foro.hub.domain.respuesta;

import com.alurachallenges.foro.hub.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "respuesta")
@Table(name = "respuestas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    private String solucion;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    private Long autorId;

}
