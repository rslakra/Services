package com.rslakra.healthcare.routinecheckup.utils.components.impl.holder;

import com.rslakra.healthcare.routinecheckup.utils.components.holder.CaptchaConstants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CaptchaConstantsImpl implements CaptchaConstants {

    @Value("${security.recaptcha.site_key}")
    private String siteKey;

    @Value("${security.recaptcha.secret_key}")
    private String secretKey;

    @Value("${security.recaptcha.url}")
    private String recaptchaUrl;

    @Value("${security.recaptcha.secret_param_name}")
    private String secretKeyParamName;

    @Value("${security.recaptcha.response_param_name}")
    private String captchaResponseParamName;

    @Value("${security.recaptcha.remoteip_param_name}")
    private String remoteIpParamName;

}
