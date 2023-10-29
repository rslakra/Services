package com.rslakra.springservices.thymeleaflayout.framework.service;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 1/27/23 11:35 AM
 */
public abstract class AbstractService<T, ID> {

    public abstract T findById(ID id);

    /**
     * @return
     */
    public abstract List<T> getAll();

    /**
     * @param t
     * @return
     */
    public abstract T create(T t);

    /**
     * @param t
     * @return
     */
    public abstract T update(T t);

    /**
     * @param id
     * @return
     */
    public abstract T delete(Long id);

}
