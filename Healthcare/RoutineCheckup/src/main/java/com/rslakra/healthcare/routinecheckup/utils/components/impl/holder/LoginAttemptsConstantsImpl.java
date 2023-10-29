package com.rslakra.healthcare.routinecheckup.utils.components.impl.holder;

import com.rslakra.healthcare.routinecheckup.utils.components.holder.LoginAttemptsConstants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class LoginAttemptsConstantsImpl implements LoginAttemptsConstants {

    @Value("${security.login.max_attempts}")
    private Integer maxAttemptsCount;

    @Value("${security.login.max_allowable_time_span_ms}")
    private Long maxAllowableTimeSpanMS;

}
