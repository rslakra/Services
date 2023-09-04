package com.rslakra.securityfilterchain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class ResourceController {

    @GetMapping({"/", "/index"})
    public String indexPage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginEndpoint() {
        return "views/login";
    }

    @GetMapping("/admin")
    public String adminEndpoint() {
        return "views/admin";
    }

    @GetMapping("/about-us")
    public String aboutUsEndpoint() {
        return "views/about-us";
    }

    @GetMapping("/contact-us")
    public String contactUsEndpoint() {
        return "views/contact-us";
    }

    @GetMapping("/user")
    public String userEndpoint() {
        return "views/home";
    }

    @GetMapping("/all")
    public String allRolesEndpoint() {
        return "views/allRoles";
    }

    @DeleteMapping("/delete")
    public String deleteEndpoint(@RequestBody String message, Model model) {
        model.addAttribute("message", "I've deleted " + message);
        return "views/delete";
    }
}
