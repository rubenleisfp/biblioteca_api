package com.castelaofp.biblioteca.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Ejemplar {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "numero_ejemplar")
	private String numeroEjemplar;
	
	@Column(name = "estado")
	private String estado;

	@ManyToOne
	@JoinColumn(name = "libro_id")
	private Libro libro;

	// getters y setters
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumeroEjemplar() {
		return numeroEjemplar;
	}

	public void setNumeroEjemplar(String numeroEjemplar) {
		this.numeroEjemplar = numeroEjemplar;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}
}