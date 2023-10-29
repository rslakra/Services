package com.rslakra.healthcare.routinecheckup.service;

import com.rslakra.healthcare.routinecheckup.keyvalue.entity.UserLoginAttempts;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:16 PM
 */
public interface UserLoginAttemptsService {

    UserLoginAttempts commitNewAttempt(String userIp);

    boolean isExtraCurrentLogin(String userIp);

}
