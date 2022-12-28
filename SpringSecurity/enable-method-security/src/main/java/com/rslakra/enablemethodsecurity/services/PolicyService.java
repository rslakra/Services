package com.rslakra.enablemethodsecurity.services;

import org.springframework.stereotype.Service;

@Service
public class PolicyService {
    
    @Policy(PolicyType.OPEN)
    public String openPolicy() {
        return "Open Policy Service";
    }

    @Policy(PolicyType.RESTRICTED)
    public String restrictedPolicy() {
        return "Restricted Policy Service";
    }
}
