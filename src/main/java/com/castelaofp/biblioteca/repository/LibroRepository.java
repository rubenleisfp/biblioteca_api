package com.castelaofp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.castelaofp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}

