package com.rslakra.melody.ews.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rohtash Lakra
 * @created 8/21/21 5:46 AM
 */
@Controller
@RequestMapping("/v1")
public class HomeControllerV1 {

    /**
     * Home Page
     *
     * @return
     */
    @GetMapping
    public String indexPage() {
        return "views/v1/home";
    }

    @GetMapping("products")
    public String productsPage() {
        return "views/v1/products";
    }

    @GetMapping("about-us")
    public String aboutUsPage() {
        return "views/v1/about-us";
    }

    @GetMapping("admin/products")
    public String adminProductsPage() {
        return "views/v1/products";
    }

    @GetMapping("admin/orders")
    public String adminOrdersPage() {
        return "views/v1/orders";
    }

}
