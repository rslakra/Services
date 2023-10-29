package com.rslakra.services.automobile.service.security;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordAuthenticationFilter.class);
    public static final String SPRING_SECURITY_FORM_USER_NAME_KEY = "userName";
    public static final String SPRING_SECURITY_FORM_DOMAIN_KEY = "domain";

    private final PasswordEncoder passwordEncoder;

    /**
     * @param passwordEncoder
     */
    public PasswordAuthenticationFilter(PasswordEncoder passwordEncoder) {
        LOGGER.debug("PasswordAuthenticationFilter({})", passwordEncoder);
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * @param servletRequest
     * @return
     */
    @Override
    protected String obtainUsername(HttpServletRequest servletRequest) {
        LOGGER.debug("obtainUsername({})", servletRequest);
        return servletRequest.getParameter(SPRING_SECURITY_FORM_USER_NAME_KEY);
    }

    /**
     * @param servletRequest
     * @return
     */
    private String obtainDomain(HttpServletRequest servletRequest) {
        return servletRequest.getParameter(SPRING_SECURITY_FORM_DOMAIN_KEY);
    }

    /**
     * @param servletRequest
     * @return
     */
    private PasswordAuthenticationToken getAuthRequestToken(HttpServletRequest servletRequest) {
        LOGGER.debug("+getAuthRequestToken({})", servletRequest);
        PasswordAuthenticationToken authRequestToken = null;
        String userName = obtainUsername(servletRequest);
        String password = obtainPassword(servletRequest);
        String domain = obtainDomain(servletRequest);

        if (BeanUtils.isEmpty(userName)) {
            throw new InvalidRequestException("Unable to obtain username from the servletRequest!");
        } else if (BeanUtils.isEmpty(password)) {
            throw new InvalidRequestException("Unable to obtain password from the servletRequest!");
        } else if (BeanUtils.isEmpty(domain)) {
            LOGGER.info("Unable to obtain domain from the servletRequest!");
        }

        authRequestToken = new PasswordAuthenticationToken(userName, password, domain);
        LOGGER.debug("-getAuthRequestToken(), authRequestToken: {}", authRequestToken);
        return authRequestToken;
    }

    /**
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest servletRequest, HttpServletResponse servletResponse)
        throws AuthenticationException {
        LOGGER.debug("+attemptAuthentication({}, {})", servletRequest, servletResponse);
        if (!HttpMethod.POST.matches(servletRequest.getMethod())) {
            throw new AuthenticationServiceException("Authentication method not supported: "
                                                     + servletRequest.getMethod());
        }

        Authentication authentication = null;
        PasswordAuthenticationToken authRequestToken = getAuthRequestToken(servletRequest);
        setDetails(servletRequest, authRequestToken);
        authentication = getAuthenticationManager().authenticate(authRequestToken);
        LOGGER.debug("-attemptAuthentication(), authentication: {}", authentication);
        return authentication;
    }

}
