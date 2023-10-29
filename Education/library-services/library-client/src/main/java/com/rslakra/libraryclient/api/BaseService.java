package com.rslakra.libraryclient.api;

import com.devamatre.framework.core.BeanUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 7:54 PM
 */
public interface BaseService<T> {

    /**
     * Returns the new <code>HttpHeaders</code> object.
     *
     * @return
     */
    static HttpHeaders newHttpHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        httpHeaders.set(BeanUtils.REQUEST_TRACER, BeanUtils.nextUuid());
        return httpHeaders;
    }


    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    T getById(final Long id);

}
