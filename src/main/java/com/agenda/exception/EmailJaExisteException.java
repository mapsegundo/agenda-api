package com.agenda.exception;

public class EmailJaExisteException extends RuntimeException {
    
    public EmailJaExisteException(String email) {
        super("O email '" + email + "' já está em uso");
    }
} 