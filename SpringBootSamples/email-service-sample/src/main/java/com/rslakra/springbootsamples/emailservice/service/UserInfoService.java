package com.rslakra.springbootsamples.emailservice.service;

import com.rslakra.springbootsamples.emailservice.domain.user.UserInfo;

/**
 * @author Rohtash Lakra
 * @created 1/6/22 5:18 PM
 */
public interface UserInfoService {

    public UserInfo getUserByName(String username);
}
