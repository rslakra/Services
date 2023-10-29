package com.rslakra.jwtauthentication7.model;

import java.io.Serializable;
import java.util.HashMap;

public class Response extends HashMap<String, Object> implements Serializable {

    private static final long serialVersionUID = -8645212270796154467L;

    public Response() {
        super();
    }

    /**
     * @param key
     * @return
     */
    public Object getValue(final String key) {
        return get(key);
    }

    /**
     * @param key
     * @return
     */
    public String getValueAsString(final String key) {
        Object value = get(key);
        return (value == null ? "" : value.toString());
    }

    /**
     * @param key
     * @param value
     */
    public void setValue(final String key, final Object value) {
        super.put(key, value);
    }

}
