package com.castelaofp.biblioteca.service;

import com.castelaofp.biblioteca.dto.UserDto;
import com.castelaofp.biblioteca.mapper.UserMapper;
import com.castelaofp.biblioteca.model.User;
import com.castelaofp.biblioteca.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;


    public List<UserDto> findAll() {
        return UserMapper.toDto(userRepository.findAll());
    }

    public Optional<User> findByEmailAndPassword(UserDto userDto) {
        User user = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        return Optional.ofNullable(user);
    }

}
