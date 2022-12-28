package com.rslakra.swaggerservice.payload;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author Rohtash Lakra
 * @created 8/5/21 11:38 AM
 */
@Component
public class PayloadBuilder extends HashMap<String, Object> {

    /**
     * @return
     */
    public static PayloadBuilder newBuilder() {
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
        return of("message", message);
    }

    /**
     * @param cause
     * @return
     */
    public PayloadBuilder withCause(final Throwable cause) {
        return of("error", cause.getLocalizedMessage());
    }

}
