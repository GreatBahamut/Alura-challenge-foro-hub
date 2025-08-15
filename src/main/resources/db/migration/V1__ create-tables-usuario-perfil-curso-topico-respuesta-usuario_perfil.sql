CREATE TABLE perfiles (
    id BIGINT AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,

    primary key(id)
);

CREATE TABLE usuarios (
    id BIGINT AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    correo_electronico VARCHAR(255) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,

    primary key(id)
);

CREATE TABLE usuario_perfil (
    usuario_id BIGINT NOT NULL,
    perfil_id BIGINT NOT NULL,

    PRIMARY KEY (usuario_id, perfil_id)
);

CREATE TABLE cursos (
    id BIGINT AUTO_INCREMENT,
    nombre VARCHAR(255) NOT NULL,
    categoria VARCHAR(255),

    primary key(id)
);

CREATE TABLE topicos (
    id BIGINT AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    mensaje TEXT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    status VARCHAR(50),
    autor_id BIGINT NOT NULL,
    curso_id BIGINT NOT NULL,

    primary key(id)
);

CREATE TABLE respuestas (
    id INT AUTO_INCREMENT,
    mensaje TEXT NOT NULL,
    topico_id INT NOT NULL,
    fecha_creacion TIMESTAMP NOT NULL,
    autor_id BIGINT NOT NULL,
    solucion TINYINT(1),

    primary key(id)
);
