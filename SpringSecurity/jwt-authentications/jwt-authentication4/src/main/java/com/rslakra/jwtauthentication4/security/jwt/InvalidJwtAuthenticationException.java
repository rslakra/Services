package com.rslakra.jwtauthentication4.security.jwt;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {


    /**
     * @param message
     * @param throwable
     */
    public InvalidJwtAuthenticationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    /**
     * @param message
     */
    public InvalidJwtAuthenticationException(String message) {
        super(message);
    }
}
