package com.fp.biblioteca.mapper;

import com.fp.biblioteca.dto.UserDto;
import com.fp.biblioteca.dto.UserRolEnum;
import com.fp.biblioteca.model.User;

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
