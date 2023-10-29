package com.rslakra.healthcare.routinecheckup.utils.components.holder;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:43 PM
 */
public interface CaptchaConstants {

    String getSiteKey();

    String getSecretKey();

    String getRecaptchaUrl();

    String getSecretKeyParamName();

    String getCaptchaResponseParamName();

    String getRemoteIpParamName();

}
