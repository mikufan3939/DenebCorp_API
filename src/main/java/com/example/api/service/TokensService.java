package com.example.api.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.api.model.User;
import com.example.api.Excepition.InvalidTokenException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokensService {

    @Value("${api.security.token.secret}")
    //@Value(value = "teste")
    private String secret;

    // Método para gerar o token JWT
    public String generateToken(User user) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("auth-api") // Emissor do token
                    .withSubject(user.getUsername()) // Define o username como subject
                    .withExpiresAt(genExpirationDate()) // Define a data de expiração do token
                    .sign(algorithm); // Assina o token com o algoritmo
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar o token JWT", exception);
        }
    }

    // Método para validar o token JWT
    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.require(algorithm)
                    .withIssuer("auth-api")
                    .build()
                    .verify(token)
                    .getSubject(); // Retorna o subject (username) do token se for válido
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException("Token inválido ou expirado");
        }
    }

    // Método para gerar a data de expiração do token (2 horas a partir da criação)
    private Instant genExpirationDate() {
        ZoneOffset systemOffset = ZoneOffset.systemDefault().getRules().getOffset(Instant.now());
        return LocalDateTime.now().plusHours(2).toInstant(systemOffset); // Adiciona 2 horas à data atual
    }
}
