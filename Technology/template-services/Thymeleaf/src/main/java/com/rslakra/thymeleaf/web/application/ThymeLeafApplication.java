package com.rslakra.thymeleaf.web.application;

import com.rslakra.thymeleaf.web.controller.HomeController;
import com.rslakra.thymeleaf.web.controller.OrderDetailsController;
import com.rslakra.thymeleaf.web.controller.OrderListController;
import com.rslakra.thymeleaf.web.controller.ProductCommentsController;
import com.rslakra.thymeleaf.web.controller.ProductListController;
import com.rslakra.thymeleaf.web.controller.SubscribeController;
import com.rslakra.thymeleaf.web.controller.ThymeLeafController;
import com.rslakra.thymeleaf.web.controller.UserProfileController;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;


public class ThymeLeafApplication {

    private TemplateEngine templateEngine;
    private Map<String, ThymeLeafController> controllersByEndpoint;


    public ThymeLeafApplication(final ServletContext servletContext) {
        super();
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);

        // HTML is the default mode, but we will set it anyway for better understanding of code
        templateResolver.setTemplateMode(TemplateMode.HTML);
        // This will convert "home" to "/WEB-INF/templates/home.html"
        templateResolver.setPrefix("/WEB-INF/templates/");
        templateResolver.setSuffix(".html");
        // Set template cache TTL to 1 hour. If not set, entries would live in cache until expelled by LRU
        templateResolver.setCacheTTLMs(Long.valueOf(3600000L));

        // Cache is set to true by default. Set to false if you want templates to
        // be automatically updated when modified.
        templateResolver.setCacheable(true);

        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);

        this.controllersByEndpoint = new HashMap<String, ThymeLeafController>();
        this.controllersByEndpoint.put("/", new HomeController());
        this.controllersByEndpoint.put("/product/list", new ProductListController());
        this.controllersByEndpoint.put("/product/comments", new ProductCommentsController());
        this.controllersByEndpoint.put("/order/list", new OrderListController());
        this.controllersByEndpoint.put("/order/details", new OrderDetailsController());
        this.controllersByEndpoint.put("/subscribe", new SubscribeController());
        this.controllersByEndpoint.put("/userprofile", new UserProfileController());

    }

    private static String getRequestPath(final HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        final String contextPath = request.getContextPath();

        final int fragmentIndex = requestURI.indexOf(';');
        if (fragmentIndex != -1) {
            requestURI = requestURI.substring(0, fragmentIndex);
        }

        if (requestURI.startsWith(contextPath)) {
            return requestURI.substring(contextPath.length());
        }
        return requestURI;
    }

    public ThymeLeafController resolveControllerForRequest(final HttpServletRequest request) {
        final String path = getRequestPath(request);
        return this.controllersByEndpoint.get(path);
    }

    public ITemplateEngine getTemplateEngine() {
        return this.templateEngine;
    }


}
