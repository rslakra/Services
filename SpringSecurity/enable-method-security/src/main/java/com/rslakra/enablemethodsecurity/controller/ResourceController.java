package com.rslakra.enablemethodsecurity.controller;

import com.rslakra.enablemethodsecurity.services.PolicyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResourceController {
    private final PolicyService policyService;

    public ResourceController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @GetMapping("/openPolicy")
    public String openPolicy() {
        return policyService.openPolicy();
    }

    @GetMapping("/restrictedPolicy")
    public String restrictedPolicy() {
        return policyService.restrictedPolicy();
    }
}
