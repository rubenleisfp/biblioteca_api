package com.fp.biblioteca.service.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

/**
 * Filtro de seguridad que intercepta las peticiones HTTP para procesar tokens JWT.
 * 
 * <p>Esta clase extiende {@link OncePerRequestFilter} de Spring Security y se encarga de:
 * <ul>
 *   <li>Extraer el token JWT del encabezado Authorization (formato "Bearer {token}")</li>
 *   <li>Validar implícitamente el token al extraer username y role mediante {@link JwtUtil}</li>
 *   <li>Establecer la autenticación en el contexto de seguridad si el token es válido</li>
 * </ul>
 * 
 * <p>La validación del token ocurre en los métodos {@code getUsername()} y {@code getRole()} 
 * de {@link JwtUtil}, que lanzan excepciones si el token es inválido o ha expirado.
 * El filtro permite el acceso a endpoints públicos cuando no se proporciona un token.
 * 
 * @author Sistema de Biblioteca
 * @version 1.0
 */
public class JwtFilter extends OncePerRequestFilter {

    /**
     * Método principal del filtro que se ejecuta para cada petición HTTP.
     * 
     * <p>Procesa el token JWT si está presente en el encabezado Authorization:
     * <ul>
     *   <li>Verifica el formato "Bearer {token}"</li>
     *   <li>Extrae username y role del token (validación implícita)</li>
     *   <li>Establece autenticación en el contexto de seguridad</li>
     * </ul>
     * 
     * <p>Nota: Si el token es inválido o expirado, las excepciones lanzadas por {@link JwtUtil}
     * no son capturadas, resultando en un error 500. Se recomienda agregar manejo de excepciones.
     * 
     * @param request Objeto HttpServletRequest que contiene la información de la petición
     * @param response Objeto HttpServletResponse para la respuesta
     * @param filterChain Cadena de filtros a continuar
     * @throws ServletException Si ocurre un error durante el filtrado
     * @throws IOException Si ocurre un error de E/S durante el filtrado
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            String token = header.substring(7);
            String username = JwtUtil.getUsername(token);
            String role = JwtUtil.getRole(token);

            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_" + role))
                    );

            SecurityContextHolder.getContext().setAuthentication(auth);
        }

        filterChain.doFilter(request, response);
    }
}
