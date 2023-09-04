package com.rslakra.componentbasedsecurity.controller;

import com.rslakra.componentbasedsecurity.payload.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    private static Logger LOGGER = LoggerFactory.getLogger(HomeController.class);

    /**
     * @return
     */
    @RequestMapping("/")
    public ResponseEntity<?> doIndex() {
        LOGGER.debug("+doIndex()");
        final Response response = new Response();
        final String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        response.setValue("message", String.format("Welcome, %s.", userName));
        LOGGER.debug("-doIndex(), response:" + response);
        return ResponseEntity.ok(response);
    }

    /**
     * @return
     */
    @RequestMapping("/home")
    public ResponseEntity<?> doHome() {
        LOGGER.debug("+doHome()");
        final Response response = new Response();
        final String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        response.setValue("message", String.format("Welcome, %s.", userName));
        LOGGER.debug("-doHome(), response:" + response);
        return ResponseEntity.ok(response);
    }

}
