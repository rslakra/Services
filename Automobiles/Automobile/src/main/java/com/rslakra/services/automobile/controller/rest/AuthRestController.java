package com.rslakra.services.automobile.controller.rest;

import com.rslakra.services.automobile.domain.entities.AutoUser;
import com.rslakra.services.automobile.dto.LoginRequest;
import com.rslakra.services.automobile.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 4/26/23 2:04 PM
 */
@RestController
@RequestMapping(value = "${restPrefix}/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuthRestController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthRestController.class);

    private final AuthService authService;

    /**
     * @param authService
     */
    @Autowired
    public AuthRestController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Registers the new autoUser.
     *
     * @param autoUser
     * @return
     */
    @PostMapping("/register")
    public AutoUser register(AutoUser autoUser) {
        LOGGER.debug("+register({})", autoUser);
        autoUser = authService.create(autoUser);
        LOGGER.debug("-register() autoUser: {}", autoUser);
        return autoUser;
    }

    /**
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public AutoUser login(@RequestBody LoginRequest loginRequest) {
        LOGGER.debug("+login({})", loginRequest);
        Optional<AutoUser> autoUserOptional = authService.login(loginRequest);
        LOGGER.debug("-login() autoUserOptional: {}", autoUserOptional);
        return autoUserOptional.get();
    }

}
