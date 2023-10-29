package com.rslakra.shipwreck.filter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author Rohtash Lakra
 * @created 8/1/23 3:54 PM
 */
@Getter
@NoArgsConstructor
@SuperBuilder
public class FilterCriteria {

    private String key;
    private String operation;
    private Object value;

    /**
     * @param operation
     * @return
     */
    public boolean equals(FilterOperation operation) {
        return (FilterOperation.ofString(getOperation()) == operation);
    }
}
