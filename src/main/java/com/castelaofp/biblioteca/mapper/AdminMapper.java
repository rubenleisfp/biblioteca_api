package com.castelaofp.biblioteca.mapper;

import com.castelaofp.biblioteca.dto.AdminDto;
import com.castelaofp.biblioteca.model.Admin;

import java.util.ArrayList;
import java.util.List;

public class AdminMapper {
    public static List<AdminDto> toDto(List<Admin> entities) {
        List<AdminDto> dtos = new ArrayList<>();
        for (Admin entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static AdminDto toDto(Admin entity) {
        return new AdminDto(entity.getEmail(), entity.getPassword());
    }

    public static Admin toEntity(AdminDto dto) {
        Admin entity = new Admin();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}
