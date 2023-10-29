package com.rslakra.healthcare.routinecheckup.utils.components.impl.holder;

import com.rslakra.healthcare.routinecheckup.utils.components.holder.RegistrationConstants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class RegistrationConstantsImpl implements RegistrationConstants {

    @Value("${security.registration.max_attempts}")
    private Integer maxAttemptsCount;

    @Value("${security.registration.max_allowable_time_span_ms}")
    private Long maxAllowableTimeSpanMS;

    @Value("${security.registration.max_registration_time_ms}")
    private Long registrationTimeMs;

    @Value("${security.registration.token.key}")
    private String registrationTokenKey;

}
