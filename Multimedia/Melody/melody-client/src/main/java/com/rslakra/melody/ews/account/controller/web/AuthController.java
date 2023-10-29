package com.rslakra.melody.ews.account.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 6:51 PM
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    /**
     * @return
     */
    @GetMapping("/login")
    public String viewLoginPage() {
        // custom logic before showing login page...
        return "login";
    }

}
