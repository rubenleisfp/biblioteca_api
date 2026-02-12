package com.fp.biblioteca.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import jakarta.servlet.http.HttpServletRequest;


	@ControllerAdvice
	public class ValidationExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {
            List<String> errors = new ArrayList<>();
            for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
                errors.add(fieldError.getField() + ": " + fieldError.getDefaultMessage());
            }
            Map<String, Object> response = new HashMap<>();
            response.put("errors", errors);
            response.put("path", request.getRequestURI());
            response.put("timestamp", System.currentTimeMillis());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
	
}