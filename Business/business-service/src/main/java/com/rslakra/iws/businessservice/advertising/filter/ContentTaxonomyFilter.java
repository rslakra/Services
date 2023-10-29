package com.rslakra.iws.businessservice.advertising.filter;

import com.rslakra.frameworks.spring.filter.AbstractFilterImpl;

import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 1:06 PM
 */
public final class ContentTaxonomyFilter extends AbstractFilterImpl {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String USER_ID = "userId";

    /**
     * @param allParams
     */
    public ContentTaxonomyFilter(Map<String, String> allParams) {
        super(allParams);
    }

}
