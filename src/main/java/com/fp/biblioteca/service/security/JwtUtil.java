package com.fp.biblioteca.service.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * Utilidad para la generación y validación de tokens JWT (JSON Web Tokens).
 * 
 * <p>Esta clase proporciona métodos estáticos para:
 * <ul>
 *   <li>Generar tokens JWT con username y role</li>
 *   <li>Extraer username de un token válido</li>
 *   <li>Extraer role de un token válido</li>
 * </ul>
 * 
 * <p>Los métodos de extracción ({@code getUsername()} y {@code getRole()}) validan
 * implícitamente el token mediante {@code parseClaimsJws()}, lanzando excepciones si:
 * <ul>
 *   <li>El token está malformado o inválido</li>
 *   <li>La firma del token es incorrecta</li>
 *   <li>El token ha expirado</li>
 * </ul>
 * 
 * @author Sistema de Biblioteca
 * @version 1.0
 */
public class JwtUtil {

    //AL MENOS 32 CARACTERES
    private static final String SECRET =
            "MOLINA_MANUEL_PABLO_NAYBET_DONATO_ROMERO_MAURO_SERGIO_VICTOR_FRAN_VALERON_MAKAAY";

    /**
     * Clave secreta convertida a bytes para uso con algoritmo HMAC-SHA256.
     */
    //CONVIERTE LA CLAVE EN BYTES
    private static final SecretKey SECRET_KEY =
            Keys.hmacShaKeyFor(SECRET.getBytes(StandardCharsets.UTF_8));

    /**
     * Genera un token JWT con el email y rol del usuario.
     * 
     * <p>El token incluye:
     * <ul>
     *   <li>Subject: email del usuario</li>
     *   <li>Claim "role": rol del usuario</li>
     *   <li>Fecha de emisión</li>
     *   <li>Fecha de expiración: 1 hora desde la generación</li>
     * </ul>
     * 
     * @param email Email del usuario que se incluirá como subject
     * @param role Rol del usuario que se incluirá como claim
     * @return Token JWT firmado con algoritmo HS256
     */
    public static String generateToken(String email, String role) {

        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hora
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    /**
     * Extrae el username (email) de un token JWT.
     * 
     * <p>Este método valida implícitamente el token. Si el token es inválido,
     * expirado o malformado, lanzará una excepción.
     * 
     * @param token Token JWT del cual extraer el username
     * @return Username (email) contenido en el subject del token
     * @throws io.jsonwebtoken.ExpiredJwtException Si el token ha expirado
     * @throws io.jsonwebtoken.MalformedJwtException Si el token está malformado
     * @throws io.jsonwebtoken.SignatureException Si la firma es inválida
     */
    public static String getUsername(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * Extrae el rol de un token JWT.
     * 
     * <p>Este método valida implícitamente el token. Si el token es inválido,
     * expirado o malformado, lanzará una excepción.
     * 
     * @param token Token JWT del cual extraer el rol
     * @return Rol del usuario contenido en el claim "role" del token
     * @throws io.jsonwebtoken.ExpiredJwtException Si el token ha expirado
     * @throws io.jsonwebtoken.MalformedJwtException Si el token está malformado
     * @throws io.jsonwebtoken.SignatureException Si la firma es inválida
     */
    public static String getRole(String token) {
        return (String) Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody()
                .get("role");
    }
}
