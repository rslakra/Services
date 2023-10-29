package com.rslakra.thymeleaftemplates.home.controller;

import com.rslakra.thymeleaftemplates.home.service.HomeService;
import com.rslakra.thymeleaftemplates.payload.LoginRequest;
import com.rslakra.thymeleaftemplates.payload.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController {

    private final HomeService homeService;

    /**
     * @param homeService
     */
    @Autowired
    public HomeController(final HomeService homeService) {
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
    @GetMapping({"/contact-us"})
    public String contactUs() {
        return "views/contact-us";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping({"/sign-in"})
    public String signIn(Model model) {
        model.addAttribute("loginRequest", new LoginRequest());
        return "views/sign-in";
    }

    /**
     * @return
     */
    @GetMapping({"/sign-up"})
    public String signUp(Model model) {
        model.addAttribute("registerRequest", new RegisterRequest());
        return "views/sign-up";
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

    /**
     * @return
     */
    @GetMapping({"/logout"})
    public String logout() {
        return "redirect:/home";
    }
}
