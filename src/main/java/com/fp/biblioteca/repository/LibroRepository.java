package com.fp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fp.biblioteca.model.Libro;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {

	@Query("SELECT l FROM Libro l WHERE l.titulo LIKE %:q% OR l.autor LIKE %:q%")
	List<Libro> findByAutorContainingOrTituloContaining(@Param("q") String q);

}