package com.rslakra.springservices.thymeleafsidebarlayouts.framework.controller.web;

/**
 * @author Rohtash Lakra
 * @created 10/15/21 6:11 PM
 */
public abstract class AbstractWebController<T> implements WebController<T> {

    private String prefix;
    private Class<T> objectType;

}
