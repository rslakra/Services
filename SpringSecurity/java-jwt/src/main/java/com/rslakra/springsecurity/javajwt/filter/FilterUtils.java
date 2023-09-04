package com.rslakra.springsecurity.javajwt.filter;

import org.springframework.security.web.csrf.CsrfToken;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rohtash Lakra
 * @created 5/1/23 5:22 PM
 */
public enum FilterUtils {
    INSTANCE;
    public static final String KEY_CSRF = "_csrf";
    public static final String KEY_POST = "POST";

    /**
     * @param servletRequest
     * @param attrName
     * @param responseType
     * @param <T>
     * @return
     */
    public static <T> T getAttribute(HttpServletRequest servletRequest, String attrName, Class<T> responseType) {
        if (Objects.nonNull(servletRequest) && Objects.nonNull(attrName)) {
            Object result = servletRequest.getAttribute(attrName);
            if (Objects.nonNull(result) && responseType.isAssignableFrom(result.getClass())) {
                return (T) result;
            }
        }

        return null;
    }

    /**
     * @param servletRequest
     * @param attrName
     * @return
     */
    public static String getAttribute(HttpServletRequest servletRequest, String attrName) {
        return getAttribute(servletRequest, attrName, String.class);
    }

    /**
     * @param servletRequest
     * @return
     */
    public static CsrfToken getCsrfAttribute(HttpServletRequest servletRequest) {
        return getAttribute(servletRequest, KEY_CSRF, CsrfToken.class);
    }

    /**
     * @param servletRequest
     * @return
     */
    public static boolean isPostRequest(HttpServletRequest servletRequest) {
        return (Objects.nonNull(servletRequest) && KEY_POST.equalsIgnoreCase(servletRequest.getMethod()));
    }


}
