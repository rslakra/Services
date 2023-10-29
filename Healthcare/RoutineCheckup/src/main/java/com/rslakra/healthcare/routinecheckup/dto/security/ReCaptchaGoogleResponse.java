package com.rslakra.healthcare.routinecheckup.dto.security;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:08 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
public class ReCaptchaGoogleResponse {

    @JsonProperty("success")
    private boolean success;

    @JsonProperty("error-codes")
    private String[] errorCodes;

}
