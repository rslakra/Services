package com.rslakra.iws.businessservice.account.filter;

import com.rslakra.frameworks.spring.filter.AbstractFilterImpl;

import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 1:06 PM
 */
public final class UserFilter extends AbstractFilterImpl {

    /**
     * @param allParams
     */
    public UserFilter(final Map<String, String> allParams) {
        super(allParams);
    }
}
