-- SQLINES DEMO *** biblioteca si no está creado
-- CREATE DATABASE ad_biblioteca;
/* SET SCHEMA 'ad_biblioteca' */;
CREATE TABLE libro (
                       id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                       titulo VARCHAR(255) NOT NULL,
                       autor VARCHAR(255) NOT NULL

);
CREATE TABLE ejemplar (
                          id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                          numero_ejemplar VARCHAR(50) NOT NULL,
                          estado VARCHAR(50) NOT NULL,
                          libro_id BIGINT,
                          FOREIGN KEY (libro_id) REFERENCES Libro(id)
);
CREATE TABLE "user" (
                        email VARCHAR(255) PRIMARY KEY,
                        password VARCHAR(255),
                        rol VARCHAR(64) NOT NULL
);