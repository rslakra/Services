package com.rslakra.thymeleaf.web.controller;

import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserProfileController implements ThymeLeafController {

    public UserProfileController() {
        super();
    }

    public void process(
        final HttpServletRequest request, final HttpServletResponse response,
        final ServletContext servletContext, final ITemplateEngine templateEngine)
        throws Exception {

        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        templateEngine.process("userprofile", ctx, response.getWriter());

    }

}
