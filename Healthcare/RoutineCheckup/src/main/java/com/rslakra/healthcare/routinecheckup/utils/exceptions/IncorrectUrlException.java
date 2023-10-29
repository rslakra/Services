package com.rslakra.healthcare.routinecheckup.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectUrlException extends RuntimeException {

    public IncorrectUrlException(String message) {
        super(message);
    }

    public IncorrectUrlException(String message, Exception e) {
        super(message, e);
    }

}
