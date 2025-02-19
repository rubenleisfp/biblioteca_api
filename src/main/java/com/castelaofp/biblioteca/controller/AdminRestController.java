package com.castelaofp.biblioteca.controller;

import com.castelaofp.biblioteca.dto.AdminDto;
import com.castelaofp.biblioteca.dto.EjemplarDto;
import com.castelaofp.biblioteca.dto.LibroDto;
import com.castelaofp.biblioteca.mapper.AdminMapper;
import com.castelaofp.biblioteca.mapper.LibroMapper;
import com.castelaofp.biblioteca.model.Admin;
import com.castelaofp.biblioteca.model.Libro;
import com.castelaofp.biblioteca.service.AdminService;
import com.castelaofp.biblioteca.service.BibliotecaService;
import com.castelaofp.biblioteca.service.exceptions.NotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * 
 */

@RestController
@RequestMapping("/api/admin")
public class AdminRestController {

	@Autowired
	private AdminService adminService;

	@Operation(summary = "Get all admins")
	@GetMapping()
	public List<AdminDto> findAll() {
		List<AdminDto> admins = adminService.findAll();
		return admins;
	}

	@Operation(summary = "Login")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = {}),
			@ApiResponse(responseCode = "400", description = "Bad request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class)) }) })
	@PostMapping("/login")
	public ResponseEntity<?> createBook(@Valid @RequestBody AdminDto adminDto) {
		Optional<Admin> optionalAdmin = adminService.findByEmailAndPassword(adminDto);
		if (optionalAdmin.isEmpty()) {
			return responseBadRequest("Invalid credentials");
		} else {
			return new ResponseEntity<>(HttpStatus.OK);
		}
	}

	private ResponseEntity<?> responseBadRequest(String message) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(message));
	}
}
