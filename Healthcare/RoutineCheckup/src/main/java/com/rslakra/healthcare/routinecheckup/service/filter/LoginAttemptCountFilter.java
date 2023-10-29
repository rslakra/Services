package com.rslakra.healthcare.routinecheckup.service.filter;

import com.rslakra.healthcare.routinecheckup.service.UserLoginAttemptsService;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.Messages;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.WebConstants;
import com.rslakra.healthcare.routinecheckup.utils.constants.ViewNames;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.AttemptsCountExceedException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:17 PM
 */

@Component
@RequiredArgsConstructor
public class LoginAttemptCountFilter extends OncePerRequestFilter {

    private final UserLoginAttemptsService userLoginAttemptsService;

    private final WebConstants webConstants;

    private final Messages messages;

    @Override
    protected void doFilterInternal(
        HttpServletRequest request,
        HttpServletResponse response,
        FilterChain filterChain
    ) throws ServletException, IOException {
        if (!isAttemptToLogin(request)) {
            filterChain.doFilter(request, response);
            return;
        }

        String userIp = request.getRemoteAddr();
        if (userLoginAttemptsService.isExtraCurrentLogin(userIp)) {
            throw new AttemptsCountExceedException(messages.getAttemptCountExceed());
        }

        filterChain.doFilter(request, response);
    }

    private boolean isAttemptToLogin(HttpServletRequest request) {
        String requestURI = request.getRequestURI();
        String fullLoginPath
            = webConstants.getBasePath() + ViewNames.LOGIN_URL;
        String method = request.getMethod().toLowerCase();
        return requestURI.equals(fullLoginPath) && "post".equals(method);
    }

}
