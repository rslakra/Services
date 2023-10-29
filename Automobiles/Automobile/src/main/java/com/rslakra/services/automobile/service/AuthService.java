package com.rslakra.services.automobile.service;

import com.devamatre.framework.spring.service.AbstractService;
import com.rslakra.services.automobile.domain.entities.AutoUser;
import com.rslakra.services.automobile.dto.LoginRequest;

import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 4/26/23 2:06 PM
 */
public interface AuthService extends AbstractService<AutoUser, Long> {

    /**
     * @param autoUser
     * @return
     */
    public AutoUser register(AutoUser autoUser);

    /**
     * @param loginRequest
     * @return
     */
    public Optional<AutoUser> login(LoginRequest loginRequest);

}
