package com.rslakra.liquibaseservice.service;

/**
 * @author Rohtash Lakra
 * @created 10/20/22 5:56 PM
 */
public abstract class AbstractService<T, ID> {

    /**
     * @param id
     * @return
     */
    public abstract T findById(ID id);
}
