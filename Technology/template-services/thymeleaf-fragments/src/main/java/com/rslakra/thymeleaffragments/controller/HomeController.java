package com.rslakra.thymeleaffragments.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    static final String VIEW_INDEX = "pages/index";

    @GetMapping(value = "/")
    public String getHome() {
        return VIEW_INDEX;
    }
}