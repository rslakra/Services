package com.rslakra.healthcare.routinecheckup.utils.components.holder;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:45 PM
 */

public interface Messages {

    String getUserNotFoundByIdTemplate();

    String getUserNotFoundByLoginTemplate();

    String getUserValidationLoginForbiddenChange();

    String getUserValidationLoginAlreadyExists();

    String getDoctorHaveNotAccess();

    String getPatientHaveNotAccess();

    String getUserHaveNotAccess();

    String getTokenExpired();

    String getCaptchaFailed();

    String getAttemptCountExceed();

    String getInternalException();

    String getIncorrectFileType();

    String getFileAlreadyExist();

    String getFileNotFound();

    String getIncorrectUrl();

    String getUrlUnverifiedSource();

    String getUnknownServiceTemplate();

}

