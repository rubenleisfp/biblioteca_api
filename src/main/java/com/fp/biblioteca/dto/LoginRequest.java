package com.fp.biblioteca.dto;


import lombok.Data;

/**
 * Clase para representar la info de login
 */
@Data
public class LoginRequest {

    private String email;
    private String password;

}
