package com.rslakra.libraryservice.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 7:54 PM
 */
public interface BaseService<T> {

    String[] IGNORED_PROPERTIES = {"id", "createdOn", "createdAt", "createdBy", "updatedOn", "updatedAt", "updatedBy"};

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    List<T> getAll();

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    T getById(final Long id);

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param pageable
     * @return
     */
    public Page<T> getByFilter(Pageable pageable);

    /**
     * Upsert file.
     *
     * @param object
     * @return
     */
    public T upsert(T object);

    /**
     * Upsert <code>objectList</code>
     *
     * @param objectList
     * @return
     */
    public List<T> upsert(List<T> objectList);

    /**
     * @param id
     */
    public void delete(Long id);

}
