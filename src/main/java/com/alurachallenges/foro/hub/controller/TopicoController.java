package com.alurachallenges.foro.hub.controller;

import com.alurachallenges.foro.hub.domain.topico.DatosPublicarTopico;
import com.alurachallenges.foro.hub.domain.topico.Topico;
import com.alurachallenges.foro.hub.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;


    @PostMapping
    public void publicarTopico(@RequestBody DatosPublicarTopico datos){

        repository.save(new Topico(datos));

    }

}
