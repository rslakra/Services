package com.rslakra.jwtauthentication2.controller;

import com.rslakra.jwtauthentication2.config.Keys;
import com.rslakra.jwtauthentication2.payload.Response;
import com.rslakra.jwtauthentication2.config.JwtUtils;
import com.rslakra.jwtauthentication2.payload.Request;
import com.rslakra.jwtauthentication2.payload.dto.UserDTO;
import com.rslakra.jwtauthentication2.service.JwtUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

    private static Logger LOGGER = LoggerFactory.getLogger(JwtAuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private JwtUserService jwtUserService;

    /**
     * Register user.
     *
     * @param user
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<?> doRegistration(@RequestBody UserDTO user) throws Exception {
        LOGGER.debug("doRegistration({})", user);
        return ResponseEntity.ok(jwtUserService.save(user));
    }

    /**
     * Authenticates the request.
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> doAuthentication(@RequestBody Request request) throws Exception {
        LOGGER.debug("doAuthentication(" + request + ")");
        authenticate(request.getUserName(), request.getPassword());
        final UserDetails userDetails = jwtUserService.loadUserByUsername(request.getUserName());
        final String accessToken = jwtUtils.generateToken(userDetails);
        final Response response = new Response();
        response.setValue(Keys.ACCESS_TOKEN, accessToken);
        return ResponseEntity.ok(response);
    }

    /**
     * Authenticates the user.
     *
     * @param userName
     * @param password
     * @throws Exception
     */
    private void authenticate(final String userName, final String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
        } catch (DisabledException ex) {
            throw new Exception("USER_DISABLED", ex);
        } catch (BadCredentialsException ex) {
            throw new Exception("INVALID_CREDENTIALS", ex);
        }
    }
}
