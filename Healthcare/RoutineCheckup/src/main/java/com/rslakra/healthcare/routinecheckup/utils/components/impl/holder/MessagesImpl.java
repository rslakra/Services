package com.rslakra.healthcare.routinecheckup.utils.components.impl.holder;

import com.rslakra.healthcare.routinecheckup.utils.components.holder.Messages;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class MessagesImpl implements Messages {

    @Value("${db.user_not_found_by_id_template}")
    private String userNotFoundByIdTemplate;

    @Value("${db.user_not_found_by_login_template}")
    private String userNotFoundByLoginTemplate;

    @Value("${user_validation.login_forbidden_change}")
    private String userValidationLoginForbiddenChange;

    @Value("${user.validation.login_already_exists}")
    private String userValidationLoginAlreadyExists;

    @Value("${doctor.have_not_access}")
    private String doctorHaveNotAccess;

    @Value("${patient.have_not_access}")
    private String patientHaveNotAccess;

    @Value("${user.have_not_access}")
    private String userHaveNotAccess;

    @Value("${jwt.token.expired}")
    private String tokenExpired;

    @Value("${captcha.failed}")
    private String captchaFailed;

    @Value("${login.attempt.exceed}")
    private String attemptCountExceed;

    @Value("${server.internal_exception}")
    private String internalException;

    @Value("${server.incorrect_file_type}")
    private String incorrectFileType;

    @Value("${server.file_already_exist}")
    private String fileAlreadyExist;

    @Value("${server.file_not_found}")
    private String fileNotFound;

    @Value("${server.url.incorrect_url}")
    private String incorrectUrl;

    @Value("${server.url.unverified_source}")
    private String urlUnverifiedSource;

    @Value("${server.service.unknown_template}")
    private String unknownServiceTemplate;

}
