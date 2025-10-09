package com.fp.biblioteca.service.security;

import com.fp.biblioteca.model.User;
import com.fp.biblioteca.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = usuarioRepository.findByEmail(username);
        if (user != null) {
            return org.springframework.security.core.userdetails.User
                    .withUsername(user.getEmail())
                    .password(user.getPassword()) // contraseña cifrada en BD
                    .roles(user.getRol()) // por ejemplo, "ADMIN" o "USER"
                    .build();
        } else {
            throw new UsernameNotFoundException("User not found");
        }



    }
}
