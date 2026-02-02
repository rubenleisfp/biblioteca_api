-- SQLite no usa CREATE DATABASE ni USE
-- Se trabaja directamente sobre el fichero .db

-- Tabla Libro
CREATE TABLE Libro (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    autor TEXT NOT NULL
);

-- Tabla Ejemplar
CREATE TABLE Ejemplar (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    numero_ejemplar TEXT NOT NULL,
    estado TEXT NOT NULL,
    libro_id INTEGER,
    FOREIGN KEY (libro_id) REFERENCES Libro(id)
);

-- Tabla User
-- "User" es una palabra reservada en muchos motores, en SQLite conviene escaparla
CREATE TABLE "User" (
    email TEXT PRIMARY KEY,
    password TEXT,
    rol TEXT NOT NULL
);