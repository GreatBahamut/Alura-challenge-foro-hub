# Foro Hub - Proyecto de Alura Challenges

Foro Hub es una aplicación de gestión de foros desarrollada en **Java con Spring Boot**, utilizando **Spring Security** para autenticación con JWT y conectada a **MySQL** como base de datos. Permite registrar usuarios, cursos, tópicos y respuestas de manera segura, con funcionalidades de CRUD y paginación.

---

## Tecnologías utilizadas

- Java 17
- Spring Boot
    - Spring Security (JWT)
    - Spring Data JPA
    - Spring Web
- MySQL
- Maven
- Lombok
- BCrypt para encriptación de contraseñas
- Auth0 Java JWT
- Insomnia para pruebas de request de la API

---

## Funcionalidades

- **Usuarios**: Registro y autenticación con JWT.
- **Cursos**: Consulta y gestión de cursos disponibles.
- **Tópicos**: Crear, listar, actualizar, eliminar y ver detalles de tópicos.
- **Respuestas**: Agregar comentarios a los tópicos.
- **Seguridad**: Solo usuarios autenticados pueden acceder a las rutas protegidas.
- **Validaciones**: Evita duplicados de título y mensaje en tópicos.

---

## Endpoints principales

| Método | Ruta | Descripción |
|--------|------|------------|
| POST | `/login` | Iniciar sesión y recibir token JWT |
| GET  | `/topicos` | Listar tópicos paginados |
| GET  | `/topicos/{id}` | Ver detalles de un tópico |
| POST | `/topicos` | Crear un nuevo tópico |
| PUT  | `/topicos/{id}` | Actualizar un tópico existente |
| DELETE | `/topicos/{id}` | Borrar un tópico |

> **Nota**: Todas las rutas, excepto `/login`, requieren token JWT en el encabezado `Authorization: Bearer <token>`. Enviar a la ruta `/login` este json `{"login" : "pipi@mail.com", "contrasena" : "123"}`

---

## Base de datos

Se utiliza MySQL con tablas principales:

- `usuarios`
- `cursos`
- `topicos`
- `respuestas`
- `perfiles` y `usuario_perfil` (para roles)

> Se incluyen datos de prueba para usuarios, cursos y tópicos.

---

## Cómo ejecutar el proyecto

1. Clonar el repositorio:
   ```bash
   git clone <URL_DEL_REPOSITORIO>
