package com.rslakra.springservices.thymeleafsidebarlayouts.home.controller;

import com.rslakra.springservices.thymeleafsidebarlayouts.home.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

/**
 * @author Rohtash Lakra
 * @created 2/3/23 1:51 PM
 */
@Controller
@RequestMapping("/")
public class IndexController {

    private final HomeService homeService;

    /**
     * @param homeService
     */
    @Autowired
    public IndexController(final HomeService homeService) {
        this.homeService = homeService;
    }

    /**
     * @param principal
     * @return
     */
    @GetMapping({"/", "index", "home"})
    public String homePage(Principal principal) {
//        return (Objects.nonNull(principal) ? "contact-us/signedIn" : "contact-us/notSignedIn");
        return "index";
    }

    /**
     * @return
     */
    @GetMapping({"/about-us"})
    public String aboutUs() {
        return "views/about-us";
    }

    /**
     * @return
     */
    @GetMapping({"/sign-in"})
    public String signedIn() {
        return "views/signedIn";
    }

    /**
     * @return
     */
    @GetMapping({"/logout"})
    public String logout() {
        return "index";
    }
}
