package com.fp.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fp.biblioteca.model.Ejemplar;
import com.fp.biblioteca.model.Libro;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {
    List<Ejemplar> findByLibro(Libro libro);
}
