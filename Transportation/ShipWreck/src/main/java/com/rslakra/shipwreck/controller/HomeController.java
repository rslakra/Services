package com.rslakra.shipwreck.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
@RestController("${restPrefix}")
public class HomeController {

    /**
     * @return
     */
    @GetMapping("/home")
    public String home() {
        return "Shipwreck welcomes you!";
    }

}
