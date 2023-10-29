package com.rslakra.thymeleaf.web.controller;

import org.thymeleaf.ITemplateEngine;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ThymeLeafController {

    /**
     * @param request
     * @param response
     * @param servletContext
     * @param templateEngine
     * @throws Exception
     */
    public void process(HttpServletRequest request, HttpServletResponse response, ServletContext servletContext,
                        ITemplateEngine templateEngine) throws Exception;

}
