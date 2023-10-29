package com.rslakra.healthcare.routinecheckup.utils.components.impl.holder;

import com.rslakra.healthcare.routinecheckup.utils.components.holder.CsrfConstants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CsrfConstantsImpl implements CsrfConstants {

    @Value("${security.csrf.parameter_name}")
    private String csrfParameterName;

    @Value("${security.csrf.header_name}")
    private String csrfHeaderName;

}
