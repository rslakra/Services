package com.rslakra.thymeleaftemplates.home.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rohtash Lakra
 * @created 8/21/21 5:46 AM
 */
@Controller
@RequestMapping("/v0")
public class HomeControllerV0 {

    /**
     * Home Page
     *
     * @return
     */
    @GetMapping
    public String homePage() {
        return "views/v0/home";
    }

    @GetMapping("about-us")
    public String aboutUsPage() {
        return "views/v0/about-us";
    }

    @GetMapping("contact-us")
    public String contactUsPage() {
        return "views/v0/contact-us";
    }

    @GetMapping("admin")
    public String adminPage() {
        return "views/v0/index";
    }

    @GetMapping("report")
    public String reportPage() {
        return "views/v0/index";
    }

    @GetMapping("setting")
    public String settingPage() {
        return "views/v0/index";
    }

    @GetMapping("task")
    public String taskPage() {
        return "views/v0/task/listTasks";
    }

}
