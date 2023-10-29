package com.rslakra.healthcare.routinecheckup.service.impl.security;

import com.rslakra.healthcare.routinecheckup.keyvalue.entity.UserCsrfToken;
import com.rslakra.healthcare.routinecheckup.keyvalue.repository.UserCsrfTokenRepository;
import com.rslakra.healthcare.routinecheckup.service.security.TokenComponent;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.CsrfConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.DefaultCsrfToken;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:22 PM
 */
@Component
@RequiredArgsConstructor
public class CustomCsrfTokenRepository implements CsrfTokenRepository {

    private final TokenComponent tokenComponent;

    private final CsrfConstants csrfConstants;

    private final UserCsrfTokenRepository userCsrfTokenRepository;

    @Override
    public CsrfToken generateToken(HttpServletRequest request) {
        String tokenString = UUID.randomUUID().toString();
        CsrfToken token = new DefaultCsrfToken(
            csrfConstants.getCsrfHeaderName(),
            csrfConstants.getCsrfParameterName(),
            tokenString
        );

        return token;
    }

    @Override
    public void saveToken(
        CsrfToken token,
        HttpServletRequest request,
        HttpServletResponse response
    ) {
        Optional<String> jwtOpt
            = tokenComponent.getTokenFromRequest(request);
        if (!jwtOpt.isPresent()) {
            return;
        }
        String jwt = jwtOpt.get();
        UserCsrfToken userCsrfToken = new UserCsrfToken(jwt, token);
        userCsrfTokenRepository.save(userCsrfToken);
    }

    @Override
    public CsrfToken loadToken(HttpServletRequest request) {
        Optional<String> jwtOpt
            = tokenComponent.getTokenFromRequest(request);
        if (!jwtOpt.isPresent()) {
            return null;
        }
        String jwt = jwtOpt.get();

        Optional<UserCsrfToken> csrfOpt
            = userCsrfTokenRepository.findById(jwt);
        if (!csrfOpt.isPresent()) {
            return null;
        }

        UserCsrfToken userCsrfToken = csrfOpt.get();
        CsrfToken token = userCsrfToken.getToken();
        return token;
    }

}
