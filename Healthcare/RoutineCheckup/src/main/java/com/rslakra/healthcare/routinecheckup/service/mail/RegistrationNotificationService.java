package com.rslakra.healthcare.routinecheckup.service.mail;

import com.rslakra.healthcare.routinecheckup.entity.UserEntity;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:24 PM
 */
public interface RegistrationNotificationService {

    String sendRegistrationEmail(UserEntity userEntity);

}
