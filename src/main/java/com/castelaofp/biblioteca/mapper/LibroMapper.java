package com.castelaofp.biblioteca.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.castelaofp.biblioteca.dto.LibroDto;
import com.castelaofp.biblioteca.model.Libro;

public class LibroMapper {

    public static List<LibroDto> toDto(List<Libro> entities) {
        List<LibroDto> dtos = new ArrayList<>();
        for (Libro entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static LibroDto toDto(Libro entity) {
    	LibroDto dto = new LibroDto(entity.getId(), entity.getTitulo(), entity.getAutor(), null);
    	if (entity.getEjemplares()!= null) {
    		dto.setEjemplares(entity.getEjemplares().stream().map(EjemplarMapper::toDto).collect(Collectors.toList()));
    	}
    	
        return dto;
    }

    public static Libro toEntity(LibroDto dto) {
    	Libro entity = new Libro();
    	entity.setTitulo(dto.getTitulo());
    	entity.setAutor(dto.getAutor());
        return entity;
    }
}