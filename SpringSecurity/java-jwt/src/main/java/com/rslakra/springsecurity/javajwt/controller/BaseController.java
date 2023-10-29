package com.rslakra.springsecurity.javajwt.controller;

import com.rslakra.springsecurity.javajwt.model.JwtResponse;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BaseController {

    /**
     * @param ex
     * @return
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({SignatureException.class, MalformedJwtException.class, JwtException.class})
    public JwtResponse exception(Exception ex) {
        JwtResponse response = new JwtResponse();
        response.setStatus(JwtResponse.Status.ERROR);
        response.setMessage(ex.getMessage());
        response.setExceptionType(ex.getClass().getName());

        return response;
    }
}
