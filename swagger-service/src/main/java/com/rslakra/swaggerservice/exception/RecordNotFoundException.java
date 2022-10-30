package com.rslakra.swaggerservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/20/21 6:19 PM
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class RecordNotFoundException extends RuntimeException {

    public RecordNotFoundException() {
        super();
    }

    /**
     * @param message
     */
    public RecordNotFoundException(String message) {
        super(message);
    }

    /**
     * @param pattern
     * @param args
     */
    public RecordNotFoundException(final String pattern, final Object... args) {
        this(String.format("No record found with %s!", String.format(pattern, args)));
    }

    /**
     * @param message
     * @param cause
     */
    public RecordNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param pattern
     * @param args
     */
    public RecordNotFoundException(final String pattern, final Throwable cause, final Object... args) {
        this(String.format("No record found with %s!", String.format(pattern, args)), cause);
    }

    /**
     * @param cause
     */
    public RecordNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public RecordNotFoundException(String message, Throwable cause,
                                   boolean enableSuppression,
                                   boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
