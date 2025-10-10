package com.fp.biblioteca.service.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final MiUserDetailsService userDetailsService;

    public SecurityConfig(MiUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
        return NoOpPasswordEncoder.getInstance();
    }

    // Sustituye al antiguo configure(HttpSecurity http)
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desactivar CSRF: no es necesario en APIs REST (no usan cookies ni formularios)
                .csrf(csrf -> csrf.disable())
                // Configurar autorización de endpoints
                .authorizeHttpRequests(auth -> auth
                        // Solo los usuarios con rol ADMIN pueden hacer GET en /user
                        .requestMatchers(HttpMethod.GET, "/api/user").hasRole("ADMIN")
                        // Cualquier otro recurso requiere estar autenticado
                        .anyRequest().authenticated()
                )
                // Habilitar autenticación básica (ideal para Postman, curl, etc.)
                .httpBasic(Customizer.withDefaults())
                // Deshabilitar gestión de sesión (API stateless)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        return http.build();
    }

    // Sustituye al antiguo configure(AuthenticationManagerBuilder auth)
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }


}
