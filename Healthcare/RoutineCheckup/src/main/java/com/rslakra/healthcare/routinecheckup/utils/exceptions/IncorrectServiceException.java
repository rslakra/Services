package com.rslakra.healthcare.routinecheckup.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class IncorrectServiceException extends RuntimeException {

    public IncorrectServiceException(String message) {
        super(message);
    }

}
