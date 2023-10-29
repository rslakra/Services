package com.rslakra.melody.ews.framework.client;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.persistence.Operation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 7:54 PM
 */
public interface AbstractClientService<T> {

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
     * @param operation
     * @param t
     * @return
     */
    T validate(Operation operation, T t);

    /**
     * @param t
     * @return
     */
    T create(T t);

    /**
     * @param ts
     * @return
     */
    List<T> create(List<T> ts);

    /**
     * @return
     */
    List<T> getAll();

    /**
     * @param filters
     * @return
     */
    List<T> getByFilter(Map<String, Object> filters);

    /**
     * @param filters
     * @param pageable
     * @return
     */
    List<T> getByFilter(Map<String, Object> filters, Pageable pageable);

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    T getById(Long id);

    /**
     * @param t
     * @return
     */
    T update(T t);

    /**
     * @param ts
     * @return
     */
    List<T> update(List<T> ts);

    /**
     * @param id
     * @return
     */
    T delete(Long id);

}
