package com.rslakra.libraryservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/20/21 6:19 PM
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateRecordException extends ServerRuntimeException {

    public DuplicateRecordException() {
        super();
    }

    /**
     * @param message
     */
    public DuplicateRecordException(String message) {
        super(message);
    }

    /**
     * @param pattern
     * @param args
     */
    public DuplicateRecordException(final String pattern, final Object... args) {
        this(String.format("Record already exists with %s!", String.format(pattern, args)));
    }

    /**
     * @param message
     * @param cause
     */
    public DuplicateRecordException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * @param pattern
     * @param args
     */
    public DuplicateRecordException(final String pattern, final Throwable cause, final Object... args) {
        this(String.format("No record found with %s!", String.format(pattern, args)), cause);
    }

    /**
     * @param cause
     */
    public DuplicateRecordException(Throwable cause) {
        super(cause);
    }

    /**
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public DuplicateRecordException(String message, Throwable cause, boolean enableSuppression,
                                    boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
