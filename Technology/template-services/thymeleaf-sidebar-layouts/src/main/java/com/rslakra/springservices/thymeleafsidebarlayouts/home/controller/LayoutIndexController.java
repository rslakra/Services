package com.rslakra.springservices.thymeleafsidebarlayouts.home.controller;

import com.rslakra.springservices.thymeleafsidebarlayouts.home.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/v1")
public class LayoutIndexController {

    private final HomeService homeService;

    /**
     * @param homeService
     */
    @Autowired
    public LayoutIndexController(final HomeService homeService) {
        this.homeService = homeService;
    }

    /**
     * @param principal
     * @return
     */
    @GetMapping
    public String homePage(Principal principal) {
        return "views/v1/index";
    }

    /**
     * @return
     */
    @GetMapping({"/about-us"})
    public String aboutUs() {
        return "views/v1/about-us";
    }

    /**
     * @return
     */
    @GetMapping({"/contact-us"})
    public String contactUs() {
        return "views/v1/contact-us";
    }

}
