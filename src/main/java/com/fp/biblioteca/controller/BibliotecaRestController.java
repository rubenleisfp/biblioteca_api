package com.fp.biblioteca.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fp.biblioteca.dto.EjemplarDto;
import com.fp.biblioteca.dto.LibroDto;

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



	@Operation(summary = "Get all books")
	@GetMapping(value = "/libros")
	public List<LibroDto> findAll() {
		throw new UnsupportedOperationException("Falta por implementar");
	}

	
	@Operation(summary = "Get a book by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Book found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = LibroDto.class)) }),
			@ApiResponse(responseCode = "404", description = "Book not found", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })

	@GetMapping(value = "/libros/{libroId}")
	public ResponseEntity<?> getById(@PathVariable("libroId") Long libroId) {

		throw new UnsupportedOperationException("Falta por implementar");
	}



	@Operation(summary = "Create a book")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Book created", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = LibroDto.class)) }),
			@ApiResponse(responseCode = "400", description = "Data not valid", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping("/libros/add")
	public ResponseEntity<LibroDto> createBook(@Valid @RequestBody LibroDto libroDto) {
		throw new UnsupportedOperationException("Falta por implementar");
		
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
		throw new UnsupportedOperationException("Falta por implementar");
	}


}
