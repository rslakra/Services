package com.rslakra.springbootsamples.emailservice.service;

import com.rslakra.springbootsamples.emailservice.dto.UserRequest;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:26 PM
 */
public interface UserService {

    /**
     * @param userRequest
     * @return
     */
    public String saveUser(UserRequest userRequest);

    /**
     * @param userId
     * @param password
     */
    public void updatePassword(String userId, String password);

}
