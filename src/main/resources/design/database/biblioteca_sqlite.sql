-- En SQLite no se crean bases de datos, se trabaja directamente sobre el fichero .db

-- Crear la tabla Libro
CREATE TABLE Libro (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    titulo TEXT NOT NULL,
    autor TEXT NOT NULL
);

-- Crear la tabla Ejemplar
CREATE TABLE Ejemplar (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    numero_ejemplar TEXT NOT NULL,
    estado TEXT NOT NULL,
    libro_id INTEGER,
    FOREIGN KEY (libro_id) REFERENCES Libro(id)
);