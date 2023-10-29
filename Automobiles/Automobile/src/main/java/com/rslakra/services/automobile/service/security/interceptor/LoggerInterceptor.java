package com.rslakra.services.automobile.service.security.interceptor;

import com.devamatre.framework.core.BeanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Enumeration;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoggerInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggerInterceptor.class);
    private static final String HASH_CODE = "hashCode";
    private static final String X_FORWARDED_FOR = "X-FORWARDED-FOR";

    /**
     * Executed before actual handler is executed
     **/
    @Override
    public boolean preHandle(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse,
                             final Object handler) throws Exception {
        final int hashCode = Objects.hashCode(servletRequest);
        servletRequest.setAttribute(HASH_CODE, hashCode);
        LOGGER.info("preHandle(), servletRequest:{}, method: {}, requestURI: {}, parameters: {}", servletRequest,
                    servletRequest.getMethod(), servletRequest.getRequestURI(), getParameters(servletRequest));
        return true;
    }

    /**
     * Executed before after handler is executed
     **/
    @Override
    public void postHandle(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse,
                           final Object handler, final ModelAndView modelAndView) throws Exception {
        LOGGER.info("postHandle(), servletRequest: {}", servletRequest);
    }

    /**
     * Executed after complete request is finished
     **/
    @Override
    public void afterCompletion(final HttpServletRequest request, final HttpServletResponse response,
                                final Object handler, final Exception ex) throws Exception {
        LOGGER.debug("+afterCompletion({}, {}, {}, {})", request, response, handler, ex);
        if (Objects.nonNull(ex)) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
            ex.printStackTrace();
        }
        LOGGER.debug("-afterCompletion()");
    }

    /**
     * @param servletRequest
     * @return
     */
    private String getParameters(final HttpServletRequest servletRequest) {
        LOGGER.debug("+getParameters({})", servletRequest);
        final StringBuffer paramBuilder = new StringBuffer();
        final Enumeration<?> parameterNames = servletRequest.getParameterNames();
        LOGGER.debug("parameterNames: {}", parameterNames);
        if (BeanUtils.isNotNull(parameterNames)) {
            paramBuilder.append("?");
            while (parameterNames.hasMoreElements()) {
                if (paramBuilder.length() > 1) {
                    paramBuilder.append("&");
                }

                final String passString = (String) parameterNames.nextElement();
                paramBuilder.append(passString).append("=");
                if (passString.contains("password") || passString.contains("answer") || passString.contains("pwd")) {
                    paramBuilder.append("*****");
                } else {
                    paramBuilder.append(servletRequest.getParameter(passString));
                }
            }
        }

        final String xForwardedFor = servletRequest.getHeader(X_FORWARDED_FOR);
        final String ipAddress = (xForwardedFor == null) ? getRemoteAddr(servletRequest) : xForwardedFor;
        LOGGER.debug("xForwardedFor: {}, ipAddress: {}", xForwardedFor, ipAddress);
        if (BeanUtils.isNotEmpty(ipAddress)) {
            paramBuilder.append("&ip=").append(ipAddress);
        }

        LOGGER.debug("-getParameters(), params: {}", paramBuilder);
        return paramBuilder.toString();
    }

    /**
     * @param servletRequest
     * @return
     */
    private String getRemoteAddr(final HttpServletRequest servletRequest) {
        final String remoteIPAddress = servletRequest.getHeader(X_FORWARDED_FOR);
        if (BeanUtils.getLength(remoteIPAddress) > 0) {
            LOGGER.debug("remoteIPAddress: {}", remoteIPAddress);
            return remoteIPAddress;
        }

        return servletRequest.getRemoteAddr();
    }
}
