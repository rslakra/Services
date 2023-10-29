package com.rslakra.shipwreck.filter;

import lombok.Getter;

import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 8/1/23 3:54 PM
 */
@Getter
public enum FilterOperation {

    EQUALS("="),
    NOT_EQUALS("!="),
    GT(">"),
    LT("="),
    LIKE(":"),
    ;

    private final String value;

    /**
     * @param value
     */
    FilterOperation(final String value) {
        this.value = value;
    }

    /**
     * @param filterOperation
     * @return
     */
    public static FilterOperation ofString(String filterOperation) {
        return Arrays.stream(values())
            .filter(entry -> entry.name().equalsIgnoreCase(filterOperation))
            .findFirst()
            .orElse(null);
    }

}
