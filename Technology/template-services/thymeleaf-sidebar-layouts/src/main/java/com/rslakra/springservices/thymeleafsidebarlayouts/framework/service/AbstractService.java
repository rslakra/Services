package com.rslakra.springservices.thymeleafsidebarlayouts.framework.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 7:54 PM
 */
public interface AbstractService<T> {

    String[] IGNORED_PROPERTIES = {
        "id", "createdOn", "createdAt", "createdBy", "updatedOn", "updatedAt", "updatedBy"
    };

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    T getById(final Long id);

    /**
     * Validates the <code>T</code> object.
     *
     * @param t
     * @return
     */
    public T validate(T t);

    /**
     * Creates the <code>T</code> object.
     *
     * @param t
     * @return
     */
    public T create(T t);

    /**
     * Creates the <code>List<T></code> objects.
     *
     * @param ts
     * @return
     */
    public List<T> create(List<T> ts);

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    List<T> getAll();

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @return
     */
    public List<T> getByFilter();

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param pageable
     * @return
     */
    public Page<T> getByFilter(Pageable pageable);

    /**
     * Updates the <code>T</code> object.
     *
     * @param t
     * @return
     */
    public T update(T t);

    /**
     * Updates the <code>List<T></code> objects.
     *
     * @param ts
     * @return
     */
    public List<T> update(List<T> ts);

    /**
     * Deletes the object by <code>id</code>.
     *
     * @param id
     * @return
     */
    public T delete(Long id);

}
