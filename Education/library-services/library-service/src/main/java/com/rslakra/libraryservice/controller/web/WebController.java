package com.rslakra.libraryservice.controller.web;

import org.springframework.ui.Model;

import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 10/15/21 6:11 PM
 */
public interface WebController<T> {

    /**
     * Saves the object.
     *
     * @param objectType
     * @return
     */
    public String save(T objectType);

    /**
     * Returns the list of <code>T</code> type objects.
     *
     * @param model
     * @return
     */
    public String listObjects(Model model);

    /**
     * Filters the objects.
     *
     * @param model
     * @return
     */
    public String filter(Model model);

    /**
     * Updates the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    public String upsert(Model model, Optional<Long> id);

    /**
     * Deletes the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    public String delete(Model model, Long id);

}
