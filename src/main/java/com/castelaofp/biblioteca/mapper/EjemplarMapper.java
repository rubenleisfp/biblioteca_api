package com.castelaofp.biblioteca.mapper;

import java.util.ArrayList;
import java.util.List;

import com.castelaofp.biblioteca.dto.EjemplarDto;
import com.castelaofp.biblioteca.model.Ejemplar;

public class EjemplarMapper {

	public static List<EjemplarDto> toDto(List<Ejemplar> entities) {
		List<EjemplarDto> dtos = new ArrayList<>();
		for (Ejemplar entity : entities) {
			dtos.add(toDto(entity));
		}
		return dtos;
	}

	public static EjemplarDto toDto(Ejemplar entity) {
		EjemplarDto dto = new EjemplarDto(entity.getId(), entity.getNumeroEjemplar(), entity.getEstado());
		return dto;
	}

	public static Ejemplar toEntity(EjemplarDto dto) {
		Ejemplar entity = new Ejemplar();
		entity.setNumeroEjemplar(dto.getNumeroEjemplar());
		entity.setEstado(dto.getEstado());
		return entity;
	}
}