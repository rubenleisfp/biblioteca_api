# Biblioteca API

Este proyecto es una API de gestión de una biblioteca, desarrollada usando Spring Boot. La API permite realizar operaciones CRUD sobre libros y ejemplares, así como realizar búsquedas y gestionar usuarios.

## Rama
En esta rama la aplicación se ejecuta bajo el protocolo https
Para ver cualquier recursos requiere estar autenticado
Para ello debe introducirse un email y password existentes en la BBDD
Para acceder a la URL api/user GET debe tener rol 'ADMIN'


## URL Base
https://localhost:8443/swagger-ui/index.html

## Estructura del Proyecto

- **Controller**: Maneja las solicitudes HTTP y las respuestas. Las principales clases son:
  - `BibliotecaRestController`: Controlador para gestionar libros y ejemplares.
  - `UserRestController`: Controlador para gestionar usuarios.

- **Service**: Contiene la lógica de negocio de la aplicación.
  - `BibliotecaService`: Proporciona métodos para gestionar libros y ejemplares.
  - `UserService`: Proporciona métodos para gestionar usuarios.

- **Repository**: Interactúa con la base de datos.
  - `LibroRepository`: Repositorio para la entidad `Libro`.
  - `EjemplarRepository`: Repositorio para la entidad `Ejemplar`.
  - `UserRepository`: Repositorio para la entidad `User`.

- **Model**: Define las entidades de la base de datos.
  - `Libro`: Entidad que representa un libro.
  - `Ejemplar`: Entidad que representa un ejemplar de un libro.
  - `User`: Entidad que representa un usuario.

- **DTO (Data Transfer Object)**: Objetos que facilitan la transferencia de datos entre las capas de la aplicación.
  - `LibroDto`: DTO para la entidad `Libro`.
  - `EjemplarDto`: DTO para la entidad `Ejemplar`.
  - `UserDto`: DTO para la entidad `User`.

- **Mapper**: Contiene las clases que convierten entidades a DTOs y viceversa.
  - `LibroMapper`: Mapea entre `Libro` y `LibroDto`.
  - `EjemplarMapper`: Mapea entre `Ejemplar` y `EjemplarDto`.
  - `UserMapper`: Mapea entre `User` y `UserDto`.

## Base de Datos

La base de datos del proyecto contiene las siguientes tablas:
- `Libro`: Almacena información sobre los libros, como título y autor.
- `Ejemplar`: Almacena información sobre los ejemplares de los libros.
- `User`: Almacena información sobre los usuarios.

# Seguridad
Se agregaron validadores para UserDto. Es necesario que se ingrese un email y un password
Se agrego un ControllerAdvice para manejar los errores

Se aplica seguridad por roles mediante Autenticación Básica.
El cliente debe enviar sus credenciales en el cabezal de autenticación con el formato `Basic <base64(username:password)>`.
El servidor verifica las credenciales y asigna el rol correspondiente al usuario.
Las credenciales de los usuarios se guardan en claro en la base de datos por simplicidad.
Cambiarlo a Bcrypt para mejorar la seguridad es sencillo, ya que solo hay que cambiarlo en SecurityConfig

Acceso a recursos por rol:
1. Para acceder a cualquier URL se debe estar autenticado
2. Para acceder a la URL api/user GET debe tener rol 'ADMIN'

## Instalación

1. Clonar el repositorio: `git clone <URL_REPOSITORIO>`
2. Navegar al directorio del proyecto: `cd biblioteca`
3. Construir el proyecto: `./mvnw clean install`
4. Ejecutar la aplicación: `./mvnw spring-boot:run`

## Uso

La API expone varios endpoints para gestionar libros y ejemplares. Puedes probar estos endpoints mediante Swagger.

Por motivos de seguridad se oculta la documentación de Swagger en el repositorio.
**URL de Swagger**: [https://localhost:8443/swagger-ui.html](http://localhost:8080/swagger-ui.html)

