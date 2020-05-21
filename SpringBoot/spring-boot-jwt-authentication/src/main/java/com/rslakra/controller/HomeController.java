package com.rslakra.controller;

import com.rslakra.model.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @RequestMapping("/home")
    public ResponseEntity<?> doHome() {
        logger.debug("+doHome()");
        final Response response = new Response();
        final String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        response.setValue("message", String.format("Welcome, %s.", userName));
        logger.debug("-doHome(), response:" + response);
        return ResponseEntity.ok(response);
    }

}