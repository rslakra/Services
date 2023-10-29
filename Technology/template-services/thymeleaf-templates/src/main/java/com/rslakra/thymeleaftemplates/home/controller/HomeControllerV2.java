package com.rslakra.thymeleaftemplates.home.controller;

import com.rslakra.thymeleaftemplates.payload.LoginRequest;
import com.rslakra.thymeleaftemplates.payload.RegisterRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rohtash Lakra
 * @created 8/21/21 5:46 AM
 */
@Controller
@RequestMapping("/v2")
public class HomeControllerV2 {

    /**
     * Home Page
     *
     * @return
     */
    @GetMapping
    public String homePage() {
        return "views/v2/index";
    }

    @GetMapping("about-us")
    public String aboutUsPage() {
        return "views/v2/about-us";
    }

    @GetMapping("contact-us")
    public String contactUsPage() {
        return "views/v2/contact-us";
    }


    /**
     * @param model
     * @return
     */
    @GetMapping({"/login"})
    public String signIn(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "views/v2/sign-in";
    }

    /**
     * @return
     */
    @GetMapping({"/register"})
    public String signUp(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "views/v2/sign-up";
    }

    /**
     * @return
     */
    @PostMapping({"/login"})
    public String login(Model model, LoginRequest loginRequest) {
        return "redirect:/home";
    }

    /**
     * @return
     */
    @PostMapping({"/register"})
    public String register(Model model, RegisterRequest registerRequest) {
        return "redirect:/sign-in";
    }


}
