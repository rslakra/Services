package com.rslakra.libraryclient.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Rohtash Lakra
 * @created 8/20/21 6:51 PM
 */
@Controller
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
