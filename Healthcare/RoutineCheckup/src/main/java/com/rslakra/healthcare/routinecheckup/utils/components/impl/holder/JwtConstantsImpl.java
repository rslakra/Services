package com.rslakra.healthcare.routinecheckup.utils.components.impl.holder;

import com.rslakra.healthcare.routinecheckup.utils.components.holder.JwtConstants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class JwtConstantsImpl implements JwtConstants {

    @Value("${security.jwt.key}")
    private String jwtKey;

    @Value("${security.jwt.expiration_time_ms}")
    private Long expirationTimeMs;

    @Value("${security.jwt.login_field_name}")
    private String loginFieldName;

    @Value("${security.jwt.parameter_name}")
    private String parameterName;

}
