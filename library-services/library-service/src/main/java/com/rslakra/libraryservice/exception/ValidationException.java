package com.rslakra.libraryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 10/16/21 4:19 PM
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Validation Failure!")
public class ValidationException extends RuntimeException {
    /**
     * @param message
     */
    public ValidationException(String message) {
        super(message);
    }

    /**
     *
     */
    public ValidationException() {
        super();
    }

    /**
     * @param message
     * @param cause
     */
    public ValidationException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param cause
     */
    public ValidationException(Throwable cause) {
        super(cause);
    }
}