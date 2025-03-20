package com.example.api.Excepition;

public class InvalidTokenException extends RuntimeException {
    public InvalidTokenException(String message) {
        super(message);  // Chama o construtor da superclasse com a mensagem
    }
}
