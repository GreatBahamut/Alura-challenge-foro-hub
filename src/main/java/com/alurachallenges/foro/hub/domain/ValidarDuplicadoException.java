package com.alurachallenges.foro.hub.domain;

public class ValidarDuplicadoException extends RuntimeException{
    public ValidarDuplicadoException(String mensaje) {
        super(mensaje);
    }
}
