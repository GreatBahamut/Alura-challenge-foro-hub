package com.alurachallenges.foro.hub.controller;

import com.alurachallenges.foro.hub.domain.usuario.DatosAutentication;
import com.alurachallenges.foro.hub.domain.usuario.Usuario;
import com.alurachallenges.foro.hub.infra.security.DatosTokeJWT;
import com.alurachallenges.foro.hub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticationController {

    // para logear usar login: "pipi@mail.com" y contraseña: "123"

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthenticationManager manager;

    @PostMapping
    public ResponseEntity iniciarSesion(@RequestBody @Valid DatosAutentication datos){

        var authenticationToken = new UsernamePasswordAuthenticationToken(datos.login(), datos.contrasena());
        var autentication = manager.authenticate(authenticationToken);

        var tokenJWT = tokenService.generarToken((Usuario) autentication.getPrincipal());

        return ResponseEntity.ok(new DatosTokeJWT(tokenJWT));
    }
}
