package com.rslakra.healthcare.routinecheckup.service;

import com.rslakra.healthcare.routinecheckup.keyvalue.entity.UserRegistrationAttempts;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:16 PM
 */
public interface UserRegistrationAttemptsService {

    UserRegistrationAttempts commitNewAttempt(String userIp);

    boolean isExtraLastRegistration(String userIp);

    boolean isExtraCurrentRegistration(String userIp);

}
