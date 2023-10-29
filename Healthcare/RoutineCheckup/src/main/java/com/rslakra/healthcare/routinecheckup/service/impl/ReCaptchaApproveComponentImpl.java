package com.rslakra.healthcare.routinecheckup.service.impl;

import com.rslakra.healthcare.routinecheckup.dto.security.ReCaptchaGoogleResponse;
import com.rslakra.healthcare.routinecheckup.service.ReCaptchaApproveComponent;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.CaptchaConstants;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.Messages;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.CaptchaFailedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestOperations;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:19 PM
 */
@Component
@RequiredArgsConstructor
public class ReCaptchaApproveComponentImpl
    implements ReCaptchaApproveComponent {

    private final RestOperations restOperations;

    private final CaptchaConstants captchaConstants;

    private final Messages messages;

    private final Pattern RESPONSE_PATTERN = Pattern.compile("[A-Za-z0-9_-]+");

    @Override
    public void approve(String captchaResponse) {
        validateResponse(captchaResponse);

        ReCaptchaGoogleResponse googleResponse
            = getCaptchaGoogleResponse(captchaResponse);

        String[] errorCodes = googleResponse.getErrorCodes();
        if (errorCodes != null && errorCodes.length > 0) {
            throw new CaptchaFailedException(messages.getCaptchaFailed());
        }
    }

    private ReCaptchaGoogleResponse getCaptchaGoogleResponse(
        String captchaResponse
    ) {
        URI uri = UriComponentsBuilder
            .fromUriString(captchaConstants.getRecaptchaUrl())
            .queryParam(
                captchaConstants.getSecretKeyParamName(),
                captchaConstants.getSecretKey()
            )
            .queryParam(
                captchaConstants.getCaptchaResponseParamName(),
                captchaResponse
            )
            .queryParam(
                captchaConstants.getRemoteIpParamName(),
                getUserIp()
            )
            .build()
            .toUri();

        ReCaptchaGoogleResponse result = restOperations.getForObject(
            uri,
            ReCaptchaGoogleResponse.class
        );
        return result;
    }

    private boolean validateResponse(String response) {
        return StringUtils.hasLength(response)
               && RESPONSE_PATTERN.matcher(response).matches();
    }

    private String getUserIp() {
        ServletRequestAttributes attribute =
            (ServletRequestAttributes) RequestContextHolder
                .currentRequestAttributes();
        HttpServletRequest request = attribute.getRequest();

        String result = request.getRemoteAddr();
        return result;
    }
}
