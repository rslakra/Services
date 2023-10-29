package com.rslakra.services.automobile.controller.web;

import com.devamatre.framework.core.BeanUtils;
import com.rslakra.services.automobile.domain.entities.AutoUser;
import com.rslakra.services.automobile.dto.LoginRequest;
import com.rslakra.services.automobile.service.AuthService;
import com.rslakra.services.automobile.service.security.context.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 4/20/23 9:34 PM
 */
@Controller
@RequestMapping
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    /**
     * @param authService
     */
    @Autowired
    public AuthController(AuthService authService) {
        LOGGER.debug("AuthController({}, {})", authService);
        this.authService = authService;
    }

    /**
     * Registers the new autoUser.
     *
     * @param autoUser
     * @return
     */
    @PostMapping("/register")
    public String register(@ModelAttribute AutoUser autoUser) {
        LOGGER.debug("+register({})", autoUser);
        autoUser = authService.register(autoUser);
        Authentication authentication = ContextUtils.INSTANCE.getAuthentication();
        LOGGER.debug("-register() authentication: {}, redirect:/", authentication);
        return "redirect:/";
    }

    /**
     * @param loginRequest
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        LOGGER.debug("+login({})", loginRequest);
        Optional<AutoUser> autoUserOptional = authService.login(loginRequest);
        Authentication authentication = ContextUtils.INSTANCE.getAuthentication();
        if (autoUserOptional.isPresent() && BeanUtils.isNotNull(authentication)) {
            LOGGER.debug("autoUserOptional: {}", autoUserOptional);
            LOGGER.debug("-login() authentication: {}, autoUserOptional: {}, redirect:/", authentication,
                         autoUserOptional);
            return "redirect:/";
        }

        return "/login?error=true";
    }

}
