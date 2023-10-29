package com.rslakra.libraryservice.payload;

import lombok.Builder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Rohtash Lakra
 * @created 8/5/21 11:38 AM
 */
@Component
public class PayloadBuilder extends ConcurrentHashMap<String, Object> {

    public static final String STATUS = "status";
    public static final String MESSAGE = "message";
    public static final String ERROR = "error";
    public static final String DELETED = "deleted";

    /**
     * @return
     */
    public static PayloadBuilder builder() {
        return new PayloadBuilder();
    }

    /**
     * @param key
     * @param value
     * @return
     */
    public PayloadBuilder of(final String key, final Object value) {
        super.put(key, value);
        return this;
    }

    /**
     * @param payloadBuilder
     * @return
     */
    public PayloadBuilder of(final PayloadBuilder payloadBuilder) {
        super.putAll(payloadBuilder);
        return this;
    }

    /**
     * @param message
     * @return
     */
    public PayloadBuilder withMessage(final String message) {
        return of(MESSAGE, message);
    }

    /**
     * @param cause
     * @return
     */
    public PayloadBuilder withCause(final Throwable cause) {
        return of(ERROR, cause.getLocalizedMessage());
    }

    /**
     *
     * @param isDeleted
     * @return
     */
    public PayloadBuilder withDeleted(final boolean isDeleted) {
        return of(DELETED, isDeleted);
    }

}
