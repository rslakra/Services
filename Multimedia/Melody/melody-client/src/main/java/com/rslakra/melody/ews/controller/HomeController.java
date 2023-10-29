package com.rslakra.melody.ews.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Rohtash Lakra
 * @created 8/21/21 5:46 AM
 */
@Controller
public class HomeController {

    /**
     * Index Page
     *
     * @return
     */
    @GetMapping({"/", "index"})
    public String indexPage() {
        return "index";
    }

    /**
     * Home Page
     *
     * @return
     */
    @GetMapping("home")
    public String homePage() {
        return "views/home";
    }

    /**
     * About-Us Page
     *
     * @return
     */
    @GetMapping("about-us")
    public String aboutUsPage() {
        return "views/about-us";
    }

}
