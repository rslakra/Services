package com.rslakra.services.automobile.filter;

import com.devamatre.framework.spring.filter.AbstractFilterImpl;

import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 4/26/23 1:05 PM
 */
public final class AppointmentFilter extends AbstractFilterImpl {

    /**
     * @param allParams
     */
    public AppointmentFilter(Map<String, Object> allParams) {
        super(allParams);
    }
}
