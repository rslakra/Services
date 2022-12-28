package com.rslakra.springbootsamples.springbootsample.service;

import com.rslakra.springbootsamples.springbootsample.domain.user.UserInfo;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 1/6/22 5:18 PM
 */
public interface UserInfoService {

    public UserInfo getUserByName(String username);
}
