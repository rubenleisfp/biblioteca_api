package com.castelaofp.biblioteca.mapper;

import com.castelaofp.biblioteca.dto.UserDto;
import com.castelaofp.biblioteca.dto.UserRolEnum;
import com.castelaofp.biblioteca.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserMapper {
    public static List<UserDto> toDto(List<User> entities) {
        List<UserDto> dtos = new ArrayList<>();
        for (User entity : entities) {
            dtos.add(toDto(entity));
        }
        return dtos;
    }

    public static UserDto toDto(User entity) {
        return new UserDto(entity.getEmail(), entity.getPassword(), UserRolEnum.valueOf(entity.getRol()));
    }

    public static User toEntity(UserDto dto) {
        User entity = new User();
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        return entity;
    }
}
