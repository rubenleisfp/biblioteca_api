package com.castelaofp.biblioteca.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castelaofp.biblioteca.dto.EjemplarDto;
import com.castelaofp.biblioteca.dto.LibroDto;
import com.castelaofp.biblioteca.mapper.LibroMapper;
import com.castelaofp.biblioteca.model.Libro;
import com.castelaofp.biblioteca.service.BibliotecaService;
import com.castelaofp.biblioteca.service.exceptions.NotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

/**
 * 
 */

@RestController
@RequestMapping("/api/biblioteca")
public class BibliotecaRestController {

	@Autowired
	private BibliotecaService bibliotecaService;



	@Operation(summary = "Get all books")
	@GetMapping(value = "/libros")
	public List<LibroDto> findAll() {
		List<Libro> libros = bibliotecaService.findAllLibros();
		return LibroMapper.toDto(libros);
	}

	
	@Operation(summary = "Get a book by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = LibroDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Book not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })

	@GetMapping(value = "/libros/{libroId}")
	public ResponseEntity<?> getById(@PathVariable("libroId") Long libroId) {

		Optional<Libro> libro = bibliotecaService.getById(libroId);
		if (libro.isPresent()) {
			LibroDto libroDto = LibroMapper.toDto(libro.get());
			return ResponseEntity.ok().body(libroDto);
		} else {
			return responseNotFound(libroId);
		}
	}



	@Operation(summary = "Create a book")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Book created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = LibroDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping("/libros/add")
	public ResponseEntity<LibroDto> createBook(@Valid @RequestBody LibroDto libroDto) {
		LibroDto dtoWithId = bibliotecaService.createLibro(libroDto);
		return new ResponseEntity<>(dtoWithId, HttpStatus.CREATED);
		
	}
	
	@Operation(summary = "Create a copy attached to book")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Copy created", content = {
			@Content(mediaType = "application/json", schema = @Schema(implementation = EjemplarDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Book not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping("/libros/{libroId}/ejemplares/add")
	public ResponseEntity<?> createCopy(@PathVariable("libroId") Long libroId,@Valid @RequestBody EjemplarDto ejemplarDto) {
		EjemplarDto ejemplarDtoCreated;
		try {
			ejemplarDtoCreated = bibliotecaService.createEjemplar(libroId, ejemplarDto);
			return new ResponseEntity<>(ejemplarDtoCreated, HttpStatus.CREATED);
		} catch (NotFoundException e) {
			return responseNotFound(e.getMessage());
		}
	}

	private ResponseEntity<?> responseNotFound(Long categoryId) {
		String errorMessage = "Category with id '" + categoryId + "' not found";
		return responseNotFound(errorMessage);
	}

	private ResponseEntity<?> responseNotFound(String message) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponse(message));
	}
}
