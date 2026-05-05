package com.fp.biblioteca.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fp.biblioteca.dto.ValidationErrorDto;
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
            ValidationErrorDto validationErrorDto = new ValidationErrorDto();
            validationErrorDto.setErrors(errors);
            validationErrorDto.setPath(request.getRequestURI());
            validationErrorDto.setTimestamp(System.currentTimeMillis());

            return new ResponseEntity<>(validationErrorDto, HttpStatus.BAD_REQUEST);
        }
	
}