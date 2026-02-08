package com.fp.biblioteca.controller;


import com.fp.biblioteca.dto.LoginRequest;
import com.fp.biblioteca.model.User;
import com.fp.biblioteca.service.security.JwtUtil;
import com.fp.biblioteca.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Optional<User> userOptional = userService.findByEmail(request.getEmail());
        if (userOptional.isEmpty()) {
            return ResponseEntity.status(401).build();
        } else{
            User user = userOptional.get();
            if ( !userService.checkPassword(request.getPassword(), user.getPassword())) {
                return ResponseEntity.status(401).build();
            } else {
                String token = JwtUtil.generateToken(user.getEmail(), user.getRol());
                return ResponseEntity.ok(Map.of("token", token));
            }
        }
    }
}
