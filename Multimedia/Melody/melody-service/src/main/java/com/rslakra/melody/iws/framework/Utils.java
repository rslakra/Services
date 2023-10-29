package com.rslakra.melody.iws.framework;

import com.devamatre.framework.core.BeanUtils;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 9:44 AM
 */
public enum Utils {
    INSTANCE;

    /**
     * @param value
     * @return
     */
    public static String addDashes(final String value) {
        if (BeanUtils.isEmpty(value)) {
            return value;
        }

        return value
            .replaceAll("([a-z])([A-Z])", "$1-$2")
            .replaceAll("_", "-")
            .toLowerCase();
    }
}
