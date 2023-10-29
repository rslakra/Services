package com.rslakra.services.automobile.service.security.interceptor;

import com.devamatre.framework.core.BeanUtils;
import com.rslakra.services.automobile.service.security.context.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class SessionTimerInterceptor implements HandlerInterceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionTimerInterceptor.class);

    private static final long MAX_INACTIVE_SESSION_TIME = 5 * 10000;
    private static final String EXECUTION_TIME_ATTR = "executionTime";

    private HttpSession httpSession;

    public SessionTimerInterceptor() {
    }

    /**
     * @param httpSession
     * @return
     */
    private Long getTimeSinceLastRequest(HttpSession httpSession) {
        return (BeanUtils.isNotNull(httpSession) ? System.currentTimeMillis() - httpSession.getLastAccessedTime()
                                                 : System.currentTimeMillis());
    }

    /**
     * Executed before actual handler is executed
     **/
    @Override
    public boolean preHandle(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse,
                             final Object handler) throws Exception {
        LOGGER.info("+preHandle({}, {}, {}), Check start time", servletRequest, servletResponse, handler);
        long startTime = System.currentTimeMillis();
        servletRequest.setAttribute(EXECUTION_TIME_ATTR, startTime);
        if (ContextUtils.isLoggedIn()) {
            httpSession = servletRequest.getSession(false);
            LOGGER.info("httpSession: {}", httpSession);
            Long timeSinceLastRequest = getTimeSinceLastRequest(httpSession);
            LOGGER.info("timeSinceLastRequest: {}", timeSinceLastRequest);
            if (BeanUtils.isNotNull(httpSession)) {
//                IFlash flash = new Flash(session);
//                flash.recycle();
            }

            LOGGER.info("Time since last servletRequest in this session: {} ms", timeSinceLastRequest);
            if (timeSinceLastRequest > MAX_INACTIVE_SESSION_TIME) {
                LOGGER.warn("Logging out, due to inactive session!");
                SecurityContextHolder.clearContext();
                servletRequest.logout();
                servletResponse.sendRedirect("/logout");
            }
        }

        LOGGER.info("-preHandle(), true");
        return true;
    }

    /**
     * Executed before after handler is executed
     **/
    @Override
    public void postHandle(final HttpServletRequest servletRequest, final HttpServletResponse servletResponse,
                           final Object handler,
                           final ModelAndView model) throws Exception {
        LOGGER.info("+postHandle({}, {}, {}), Check execution time.", servletRequest, servletResponse, handler);
        long startTime = (Long) servletRequest.getAttribute("executionTime");
        LOGGER.info("-postHandle(), Execution took {} millis.", (System.currentTimeMillis() - startTime));
    }
}
