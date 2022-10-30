package com.rslakra.libraryservice.exception;

import com.rslakra.libraryservice.utils.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 10/16/21 4:19 PM
 */
@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Insufficient Fund!")
public class InsufficientFundException extends ServerException {

    public static final String DEFAULT_MESSAGE = "Insufficient Fund!";

    public InsufficientFundException() {
        this(DEFAULT_MESSAGE);
    }

    /**
     * @param message
     */
    public InsufficientFundException(String message) {
        super(nullSafeMessage(message,DEFAULT_MESSAGE));
    }

    /**
     * @param message
     * @param throwable
     */
    public InsufficientFundException(String message, Throwable throwable) {
        super(nullSafeMessage(message, DEFAULT_MESSAGE), throwable);
    }
}
