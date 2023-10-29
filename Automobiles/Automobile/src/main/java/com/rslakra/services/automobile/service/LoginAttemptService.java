package com.rslakra.services.automobile.service;

import com.rslakra.services.automobile.service.security.context.ContextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Rohtash Lakra
 * @created 4/21/23 3:56 PM
 */
@Service
public class LoginAttemptService {

    public static final int MAX_ATTEMPT = 10;
    private LoadingCache<String, Integer, Integer> cacheAttempts;

    @Autowired
    private HttpServletRequest servletRequest;

    public LoginAttemptService() {
        super();
//        cacheAttempts =
//            CacheBuilder.newBuilder().expireAfterWrite(1, TimeUnit.DAYS).build(new CacheLoader<String, Integer>() {
//                @Override
//                public Integer load(final String key) {
//                    return 0;
//                }
//            });
    }

    /**
     * @param key
     */
    public void loginFailed(final String key) {
        int attempts;
//        try {
//            attempts = cacheAttempts.get(key);
//        } catch (final ExecutionException e) {
//            attempts = 0;
//        }
//        attempts++;
//        cacheAttempts.put(key, attempts);
    }

    public boolean isBlocked() {
//        try {
//            return cacheAttempts.get(getClientIP()) >= MAX_ATTEMPT;
//        } catch (final ExecutionException ex) {
//            return false;
//        }
        return false;
    }

    private String getClientIP() {
        final String xfHeader = servletRequest.getHeader(ContextUtils.X_FORWARDED_FOR);
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return servletRequest.getRemoteAddr();
    }
}