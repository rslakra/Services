package com.rslakra.healthcare.routinecheckup.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class UserMismatchException extends RuntimeException {

    public UserMismatchException(String message) {
        super(message);
    }

}
