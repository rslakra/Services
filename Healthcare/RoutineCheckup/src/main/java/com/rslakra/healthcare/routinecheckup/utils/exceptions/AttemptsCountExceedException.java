package com.rslakra.healthcare.routinecheckup.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class AttemptsCountExceedException extends AuthenticationException {

    public AttemptsCountExceedException(String message) {
        super(message);
    }

}
