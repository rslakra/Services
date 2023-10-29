package com.rslakra.springbootexample;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Rohtash Lakra
 * @created 5/8/23 12:34 PM
 */
@RestController
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "Hello, Home Page!";
    }
}