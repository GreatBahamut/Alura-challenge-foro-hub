package com.alurachallenges.foro.hub.domain.usuario;

public record UsuarioDTO(
        Long id,
        String nombre,
        String correoElectronico
) {

    public UsuarioDTO (Usuario usuario){

        this(
                usuario.getId(),
                usuario.getNombre(),
                usuario.getCorreoElectronico()
        );
    }

}
