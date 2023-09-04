package com.rslakra.jwtauthentication4.web;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.status;

import com.rslakra.jwtauthentication4.security.jwt.InvalidJwtAuthenticationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class RestExceptionHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(RestExceptionHandler.class);

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {VehicleNotFoundException.class})
    public ResponseEntity vehicleNotFound(VehicleNotFoundException ex, WebRequest request) {
        LOGGER.debug("handling VehicleNotFoundException...");
        return notFound().build();
    }

    /**
     * @param ex
     * @param request
     * @return
     */
    @ExceptionHandler(value = {InvalidJwtAuthenticationException.class})
    public ResponseEntity invalidJwtAuthentication(InvalidJwtAuthenticationException ex, WebRequest request) {
        LOGGER.debug("handling InvalidJwtAuthenticationException...");
        return status(UNAUTHORIZED).build();
    }
}
