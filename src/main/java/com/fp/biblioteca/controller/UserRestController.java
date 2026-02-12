package com.fp.biblioteca.controller;

import com.fp.biblioteca.dto.UserDto;
import com.fp.biblioteca.dto.ValidationErrorDto;
import com.fp.biblioteca.model.User;
import com.fp.biblioteca.service.UserService;
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
@RequestMapping("/api/user")
public class UserRestController {

	@Autowired
	private UserService userService;

	@Operation(summary = "Get all users")
	@GetMapping()
	public List<UserDto> findAll() {
		List<UserDto> users = userService.findAll();
		return users;
	}

	@Operation(summary = "Login")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "OK", content = {}),
			@ApiResponse(responseCode = "400", description = "Bad request", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = ValidationErrorDto.class)) }) })
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody UserDto adminDto) {
		Optional<User> optionalUser = userService.findByEmailAndPassword(adminDto);
        return new ResponseEntity<>(HttpStatus.OK);
	}

	private ResponseEntity<?> responseBadRequest(String message) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(message));
	}
}
