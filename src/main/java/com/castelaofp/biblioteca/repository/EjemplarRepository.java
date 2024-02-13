package com.castelaofp.biblioteca.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castelaofp.biblioteca.model.Ejemplar;
import com.castelaofp.biblioteca.model.Libro;

public interface EjemplarRepository extends JpaRepository<Ejemplar, Long> {
    List<Ejemplar> findByLibro(Libro libro);
}
