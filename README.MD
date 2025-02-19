# Biblioteca API

Este proyecto es una API de gestión de una biblioteca, desarrollada usando Spring Boot. La API permite realizar operaciones CRUD sobre libros y ejemplares, así como realizar búsquedas y gestionar administradores.

## Estructura del Proyecto

- **Controller**: Maneja las solicitudes HTTP y las respuestas. Las principales clases son:
  - `BibliotecaRestController`: Controlador para gestionar libros y ejemplares.
  - `AdminRestController`: Controlador para gestionar administradores.

- **Service**: Contiene la lógica de negocio de la aplicación.
  - `BibliotecaService`: Proporciona métodos para gestionar libros y ejemplares.
  - `AdminService`: Proporciona métodos para gestionar administradores.

- **Repository**: Interactúa con la base de datos.
  - `LibroRepository`: Repositorio para la entidad `Libro`.
  - `EjemplarRepository`: Repositorio para la entidad `Ejemplar`.
  - `AdminRepository`: Repositorio para la entidad `Admin`.

- **Model**: Define las entidades de la base de datos.
  - `Libro`: Entidad que representa un libro.
  - `Ejemplar`: Entidad que representa un ejemplar de un libro.
  - `Admin`: Entidad que representa un administrador.

- **DTO (Data Transfer Object)**: Objetos que facilitan la transferencia de datos entre las capas de la aplicación.
  - `LibroDto`: DTO para la entidad `Libro`.
  - `EjemplarDto`: DTO para la entidad `Ejemplar`.
  - `AdminDto`: DTO para la entidad `Admin`.

- **Mapper**: Contiene las clases que convierten entidades a DTOs y viceversa.
  - `LibroMapper`: Mapea entre `Libro` y `LibroDto`.
  - `EjemplarMapper`: Mapea entre `Ejemplar` y `EjemplarDto`.
  - `AdminMapper`: Mapea entre `Admin` y `AdminDto`.

## Base de Datos

La base de datos del proyecto contiene las siguientes tablas:
- `Libro`: Almacena información sobre los libros, como título y autor.
- `Ejemplar`: Almacena información sobre los ejemplares de los libros.
- `Admin`: Almacena información sobre los administradores.

## Instalación

1. Clonar el repositorio: `git clone <URL_REPOSITORIO>`
2. Navegar al directorio del proyecto: `cd biblioteca`
3. Construir el proyecto: `./mvnw clean install`
4. Ejecutar la aplicación: `./mvnw spring-boot:run`

## Uso

La API expone varios endpoints para gestionar libros y ejemplares. Puedes probar estos endpoints mediante Swagger.

**URL de Swagger**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Contribución

Si deseas contribuir a este proyecto, por favor sigue los siguientes pasos:
1. Haz un fork del repositorio.
2. Crea una rama con tu nueva funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3. Haz commit de tus cambios (`git commit -am 'Añadir nueva funcionalidad'`).
4. Empuja la rama (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

