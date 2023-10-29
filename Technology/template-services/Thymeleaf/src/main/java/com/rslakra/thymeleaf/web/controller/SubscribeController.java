package com.rslakra.thymeleaf.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscribeController implements ThymeLeafController {

    public SubscribeController() {
        super();
    }

    public void process(
        final HttpServletRequest request, final HttpServletResponse response,
        final ServletContext servletContext, final ITemplateEngine templateEngine)
        throws Exception {

        WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        templateEngine.process("subscribe", ctx, response.getWriter());

    }

}
