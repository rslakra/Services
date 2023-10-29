package com.rslakra.melody.iws.account.filter;

import com.rslakra.frameworks.spring.filter.AbstractFilterImpl;

import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 1:06 PM
 */
public final class RoleFilter extends AbstractFilterImpl {

    /**
     * @param allParams
     */
    public RoleFilter(Map<String, String> allParams) {
        super(allParams);
    }
}
