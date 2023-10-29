package com.rslakra.services.automobile.service.security.interceptor;

import com.rslakra.services.automobile.service.security.context.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.SmartView;
import org.springframework.web.servlet.View;

import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserInterceptor implements HandlerInterceptor {

    private static Logger LOGGER = LoggerFactory.getLogger(UserInterceptor.class);

    /**
     * Executed before actual handler is executed
     *
     * @param servletRequest
     * @param servletResponse
     * @param object
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object object)
        throws Exception {
        LOGGER.debug("+preHandle({}, {}, {})", servletRequest, servletResponse, object);
        if (ContextUtils.isLoggedIn()) {
            addToModelOrSession(servletRequest.getSession());
        }
        LOGGER.debug("-preHandle(), true");
        return true;
    }

    /**
     * Executed before after handler is executed. If view is a redirect view, we don't need to execute postHandle
     **/
    @Override
    public void postHandle(HttpServletRequest servletRequest, HttpServletResponse servletResponse, Object object,
                           ModelAndView modelAndView)
        throws Exception {
        LOGGER.debug("+postHandle({}, {}, {})", servletRequest, servletResponse, modelAndView);
        if (modelAndView != null && !isRedirectView(modelAndView)) {
            if (ContextUtils.isLoggedIn()) {
                addToModelOrSession(modelAndView);
            }
        }
        LOGGER.debug("-postHandle()");
    }

    /**
     * Used before model is generated, based on httpSession
     *
     * @param httpSession
     */
    private void addToModelOrSession(HttpSession httpSession) {
        LOGGER.info("+addToModelOrSession({})", httpSession);
        String loggedInUsername = ContextUtils.INSTANCE.getAuthentication().getName();
        httpSession.setAttribute("username", loggedInUsername);
        LOGGER.info("-addToModelOrSession(), loggedInUsername: {}", loggedInUsername);
    }

    /**
     * Used when modelAndView is available
     *
     * @param modelAndView
     */
    private void addToModelOrSession(ModelAndView modelAndView) {
        LOGGER.info("+addToModelOrSession({})", modelAndView);
        String loggedInUsername = ContextUtils.getLoggedInUsername();
        modelAndView.addObject("loggedInUsername", loggedInUsername);
        LOGGER.info("-addToModelOrSession(), loggedInUsername: {}, session: {}", loggedInUsername,
                    modelAndView.getModel());
    }

    /**
     * @param modelAndView
     * @return
     */
    public static boolean isRedirectView(ModelAndView modelAndView) {
        LOGGER.debug("+isRedirectView({})", modelAndView);
        boolean redirectView = false;
        String viewName = modelAndView.getViewName();
        if (Objects.nonNull(viewName) && viewName.startsWith("redirect:/")) {
            redirectView = true;
        } else {
            View view = modelAndView.getView();
            redirectView = (view != null && view instanceof SmartView && ((SmartView) view).isRedirectView());
        }

        LOGGER.debug("-isRedirectView(), redirectView: {}", redirectView);
        return redirectView;
    }

}
