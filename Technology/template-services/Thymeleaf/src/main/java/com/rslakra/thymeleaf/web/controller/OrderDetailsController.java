package com.rslakra.thymeleaf.web.controller;

import com.rslakra.thymeleaf.persistence.entities.Order;
import com.rslakra.thymeleaf.service.OrderService;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderDetailsController implements ThymeLeafController {

    public OrderDetailsController() {
        super();
    }

    public void process(
        final HttpServletRequest request, final HttpServletResponse response,
        final ServletContext servletContext, final ITemplateEngine templateEngine)
        throws Exception {

        final Long orderId = Long.valueOf(request.getParameter("orderId"));

        final OrderService orderService = new OrderService();
        final Order order = orderService.findById(orderId);

        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("order", order);

        templateEngine.process("order/details", ctx, response.getWriter());

    }

}
