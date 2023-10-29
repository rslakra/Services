package com.rslakra.liquibaseservice.exception;

/**
 * @author Rohtash Lakra
 * @created 10/20/22 6:04 PM
 */
public class NoRecordFoundException extends RuntimeException {

    public NoRecordFoundException() {
        this("No Record Found!");
    }

    /**
     * @param format
     * @param objects
     */
    public NoRecordFoundException(String format, Object... objects) {
        super(String.format("No Record Found for %s!", objects));
    }

    public NoRecordFoundException(String message) {
        super(message);
    }

    public NoRecordFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public NoRecordFoundException(Throwable cause) {
        super(cause);
    }

    protected NoRecordFoundException(String message, Throwable cause, boolean enableSuppression,
                                     boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
