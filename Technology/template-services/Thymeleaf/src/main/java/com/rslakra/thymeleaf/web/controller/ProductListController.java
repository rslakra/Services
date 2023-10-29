package com.rslakra.thymeleaf.web.controller;

import com.rslakra.thymeleaf.persistence.entities.Product;
import com.rslakra.thymeleaf.service.ProductService;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.context.WebContext;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductListController implements ThymeLeafController {

    public ProductListController() {
        super();
    }

    public void process(
        final HttpServletRequest request, final HttpServletResponse response,
        final ServletContext servletContext, final ITemplateEngine templateEngine)
        throws Exception {

        final ProductService productService = new ProductService();
        final List<Product> allProducts = productService.findAll();

        final WebContext ctx = new WebContext(request, response, servletContext, request.getLocale());
        ctx.setVariable("prods", allProducts);

        templateEngine.process("product/list", ctx, response.getWriter());

    }

}
