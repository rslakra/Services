package com.rslakra.libraryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/20/21 6:19 PM
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Invalid Request!")
public class InvalidRequestException extends ServerRuntimeException {

    public InvalidRequestException() {
        super();
    }

    /**
     * @param message
     */
    public InvalidRequestException(String message) {
        super(message);
    }

    /**
     * @param pattern
     * @param args
     */
    public InvalidRequestException(final String pattern, final Object... args) {
        this(String.format("No record found with %s!", String.format(pattern, args)));
    }

    /**
     * @param message
     * @param cause
     */
    public InvalidRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param pattern
     * @param args
     */
    public InvalidRequestException(final String pattern, final Throwable cause, final Object... args) {
        this(String.format("No record found with %s!", String.format(pattern, args)), cause);
    }

}
