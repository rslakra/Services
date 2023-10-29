package com.rslakra.springservices.thymeleafsidebar.home.controller;

import com.rslakra.springservices.thymeleafsidebar.framework.controller.AbstractController;
import com.rslakra.springservices.thymeleafsidebar.home.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/")
public class HomeController extends AbstractController {

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
    public String index(Principal principal) {
//        return (Objects.nonNull(principal) ? "home/signedIn" : "home/notSignedIn");
        return "index";
    }

    /**
     * @return
     */
    @GetMapping({"/about-us"})
    public String aboutUs() {
        return "home/about-us";
    }

    /**
     * @return
     */
    @GetMapping({"/contact-us"})
    public String contactUs() {
        return "home/contact-us";
    }

    /**
     * @return
     */
    @GetMapping({"/sign-in"})
    public String signedIn() {
        return "home/signedIn";
    }

    /**
     * @return
     */
    @GetMapping({"/logout"})
    public String logout() {
        return "index";
    }
}
