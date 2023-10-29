package com.rslakra.healthcare.routinecheckup.utils.components.impl.holder;

import com.rslakra.healthcare.routinecheckup.utils.components.holder.WebConstants;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class WebConstantsImpl implements WebConstants {

    @Value("${server.servlet.context-path}")
    private String basePath;

    @Value("${server.domain_name}")
    private String domainName;

    @Value("${server.port}")
    private Integer appPort;

    @Value("${server.cookie.same_site}")
    private String sameSite;

}
