package com.rslakra.healthcare.routinecheckup.utils.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CaptchaFailedException extends RuntimeException {

    public CaptchaFailedException(String message) {
        super(message);
    }

}
