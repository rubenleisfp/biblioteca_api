package com.castelaofp.biblioteca.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.castelaofp.biblioteca.dto.EjemplarDto;
import com.castelaofp.biblioteca.dto.LibroDto;
import com.castelaofp.biblioteca.mapper.EjemplarMapper;
import com.castelaofp.biblioteca.mapper.LibroMapper;
import com.castelaofp.biblioteca.model.Ejemplar;
import com.castelaofp.biblioteca.model.Libro;
import com.castelaofp.biblioteca.repository.EjemplarRepository;
import com.castelaofp.biblioteca.repository.LibroRepository;
import com.castelaofp.biblioteca.service.exceptions.NotFoundException;


@Service
public class BibliotecaService {

	private static Logger LOG = LoggerFactory.getLogger(BibliotecaService.class);

	@Autowired
	private LibroRepository libroRepository;

	@Autowired
	private EjemplarRepository ejemplarRepository;

	/**
	 * Crea un nuevo libro
	 * 
	 * @param libroDto
	 * @return
	 */
	public LibroDto createLibro(LibroDto libroDto) {
		Libro libro = LibroMapper.toEntity(libroDto);
		libro = libroRepository.save(libro);
		LibroDto dtoCreated = LibroMapper.toDto(libro);
		return dtoCreated;
	};

	/**
	 * Crea un ejemplar nuevo asociado al libro recibida como argumento
	 * 
	 * Devuelve el ejemplar recien creado
	 * 
	 * @param libroId
	 * @param ejemplarDto
	 * @return
	 * @throws NotFoundException
	 */
	public EjemplarDto createEjemplar(Long libroId, EjemplarDto ejemplarDto) throws NotFoundException {
		Optional<Libro> libro = libroRepository.findById(libroId);
		if (libro.isEmpty()) {
			LOG.error("No existe el libro con id: " + libro);
			LOG.error("EjemplarDto " + ejemplarDto);
			throw new NotFoundException("No existe el libro con id: " + libro);
		} else {

			Ejemplar ejemplar = EjemplarMapper.toEntity(ejemplarDto);
			ejemplar.setLibro(libro.get());
			ejemplar = ejemplarRepository.save(ejemplar);
			ejemplarDto = EjemplarMapper.toDto(ejemplar);

			return ejemplarDto;
		}
	}

	/**
	 * Devuelve la lista con todos los libros
	 * 
	 * @return
	 */
	public List<LibroDto> findAllLibros() {
		return LibroMapper.toDto(libroRepository.findAll());

	}

	/**
	 * Busca los libros que contenga la palabra recibida como argumento en su titulo o en su autor
	 * @param q
	 * @return
	 */
	public List<LibroDto> searchLibros(String q) {
		List<Libro> booksFound = libroRepository.findByAutorContainingOrTituloContaining(q);
		List<LibroDto> booksFoundDto = LibroMapper.toDto(booksFound);
		return booksFoundDto;
	}

	/**
	 * Para el id proporcionado, devuelve el libro si existe
	 * 
	 * @param libroId
	 * @return
	 */
	public Optional<LibroDto> getById(Long libroId) {
		Optional<Libro> libro = libroRepository.findById(libroId);
		if (libro.isPresent()) {
			return Optional.of(LibroMapper.toDto(libro.get()));
		} else {
			LOG.warn("No se encontró el libro con id: " + libroId);
			return Optional.empty();
		}

	}


	/**
	 * Actualiza el libro con el id proporcionado con los valores de
	 * <code>libroDto</code>.
	 *
	 * @param libroId
	 *            el id del libro a actualizar
	 * @param libroDto
	 *            el objeto que contiene los valores a actualizar
	 * @return el objeto {@link LibroDto} actualizado si el libro existe, o
	 *         {@link Optional#empty()} si no existe
	 */
	public Optional<LibroDto> update(Long libroId, LibroDto libroDto) {
		Optional<Libro> optionalBook = libroRepository.findById(libroId);
		if (optionalBook.isPresent()) {
			Libro libro = optionalBook.get();
			// Actualizar los valores de la entidad
			libro.setTitulo(libroDto.getTitulo());
			libro.setAutor(libroDto.getAutor());

			// Guardar la entidad actualizada
			Libro libroActualizado = libroRepository.save(libro);

			// Convertir a DTO antes de devolver
			return Optional.of(LibroMapper.toDto(libroActualizado));
		} else {
			return Optional.empty();
		}
	}



	/**
	 * Borra el libro con el id proporcionado.
	 *
	 * @param libroId el id del libro a borrar
	 * @return {@code true} si el libro ha sido borrado, {@code false} si no existe
	 */
	public boolean delete(Long libroId) {
		Optional<Libro> optionalBook = libroRepository.findById(libroId);
		if (optionalBook.isPresent()) {
			libroRepository.deleteById(libroId);
			return true;
		} else {
			return false;
		}
	}
}