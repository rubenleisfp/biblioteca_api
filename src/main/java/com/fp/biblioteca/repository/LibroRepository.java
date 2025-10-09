package com.fp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}

