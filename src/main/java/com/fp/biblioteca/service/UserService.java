package com.fp.biblioteca.service;

import com.fp.biblioteca.dto.UserDto;
import com.fp.biblioteca.mapper.UserMapper;
import com.fp.biblioteca.model.User;
import com.fp.biblioteca.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private static Logger LOG = LoggerFactory.getLogger(UserService.class);

    //private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;


    public List<UserDto> findAll() {
        return UserMapper.toDto(userRepository.findAll());
    }

    public Optional<User> findByEmailAndPassword(UserDto userDto) {
        User user = userRepository.findByEmailAndPassword(userDto.getEmail(), userDto.getPassword());
        return Optional.ofNullable(user);
    }

    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(userRepository.findByEmail(email));
    }

    public boolean checkPassword(String rawPassword, String encodedPassword) {
        //return encoder.matches(rawPassword, encodedPassword);
        return rawPassword.equals(encodedPassword);
    }

}
