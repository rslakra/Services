package com.rslakra.springsecurity.javajwt.controller.rest;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import com.rslakra.springsecurity.javajwt.controller.BaseController;
import com.rslakra.springsecurity.javajwt.service.SecretsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class SecretsController extends BaseController {

    private final SecretsService secretsService;

    /**
     * @param secretsService
     */
    @Autowired
    public SecretsController(SecretsService secretsService) {
        this.secretsService = secretsService;
    }

    @RequestMapping(value = "/get-secrets", method = GET)
    public Map<String, String> getSecrets() {
        return secretsService.getSecrets();
    }

    @RequestMapping(value = "/refresh-secrets", method = GET)
    public Map<String, String> refreshSecrets() {
        return secretsService.refreshSecrets();
    }

    @RequestMapping(value = "/set-secrets", method = POST)
    public Map<String, String> setSecrets(@RequestBody Map<String, String> secrets) {
        secretsService.setSecrets(secrets);
        return secretsService.getSecrets();
    }
}
