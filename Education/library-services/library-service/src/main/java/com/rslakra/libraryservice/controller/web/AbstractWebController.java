package com.rslakra.libraryservice.controller.web;

import org.springframework.ui.Model;

import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 10/15/21 6:11 PM
 */
public abstract class AbstractWebController<T> implements WebController<T> {

    private String prefix;
    private Class<T> objectType;

    /**
     * @param objectType
     * @return
     */
    @Override
    public String save(T objectType) {
        return null;
    }

    /**
     * @param model
     * @return
     */
    @Override
    public String listObjects(Model model) {
        return null;
    }

    /**
     * @param model
     * @return
     */
    @Override
    public String filter(Model model) {
        return null;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @Override
    public String upsert(Model model, Optional<Long> id) {
        return null;
    }

    /**
     * @param model
     * @param id
     * @return
     */
    @Override
    public String delete(Model model, Long id) {
        return null;
    }
}
