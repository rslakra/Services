package com.rslakra.springservices.thymeleafsidebarlayouts.admin.controller;

import com.rslakra.springservices.thymeleafsidebarlayouts.admin.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final AdminService adminService;

    /**
     * @param adminService
     */
    @Autowired
    public AdminController(final AdminService adminService) {
        this.adminService = adminService;
    }

    /**
     * @param principal
     * @return
     */
    @GetMapping
    public String adminIndex(Principal principal) {
//        return (Objects.nonNull(principal) ? "home/signedIn" : "home/notSignedIn");
        return "views/admin/index";
    }

}
