package com.rslakra.springbootsamples.springbootsample.service;

import com.rslakra.springbootsamples.springbootsample.dto.UserRequest;

/**
 * @author Rohtash Lakra (rlakra)
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
