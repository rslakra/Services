package com.rslakra.services.automobile.service.security;

import com.devamatre.framework.core.BeanUtils;
import com.rslakra.services.automobile.service.security.context.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

public class UserDetailsAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailsAuthenticationProvider.class);

    /**
     * The plaintext password used to perform PasswordEncoder#matches(CharSequence, String)}  on when the user is not
     * found to avoid SEC-2056.
     */
    private static final String USER_NOT_FOUND_PASSWORD = "userNotFoundPassword";

    private PasswordEncoder passwordEncoder;
    private UserDetailsService userDetailsService;

    /**
     * The password used to perform {@link PasswordEncoder#matches(CharSequence, String)} on when the user is not found
     * to avoid SEC-2056. This is necessary, because some {@link PasswordEncoder} implementations will short circuit if
     * the password is not in a valid format.
     */
    private String userNotFoundEncodedPassword;

    /**
     * @param passwordEncoder
     * @param userDetailsService
     */
    public UserDetailsAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailsService userDetailsService) {
        LOGGER.debug("UserDetailsAuthenticationProvider({}, {})", passwordEncoder, userDetailsService);
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    /**
     * @param userDetails
     * @param authentication
     * @throws AuthenticationException
     */
    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {
        LOGGER.debug("+additionalAuthenticationChecks({}, {})", userDetails, authentication);
        if (BeanUtils.isNull(authentication.getCredentials())) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(
                messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        String passwordProvided = ContextUtils.getAuthenticatedPassword(authentication);
        LOGGER.debug("passwordProvided: {}", passwordProvided);
        if (!passwordEncoder.matches(passwordProvided, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(
                messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        LOGGER.debug("-additionalAuthenticationChecks()");
    }

    /**
     * @throws Exception
     */
    @Override
    protected void doAfterPropertiesSet() throws Exception {
        Assert.notNull(userDetailsService, "A userDetailsService should be set!");
        this.userNotFoundEncodedPassword = this.passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
    }

    /**
     * @param userName
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {
        LOGGER.debug("+retrieveUser({}, {})", userName, authentication);
        PasswordAuthenticationToken passwordAuthToken = (PasswordAuthenticationToken) authentication;
        UserDetails loadedUserDetails;
        try {
            loadedUserDetails = userDetailsService.loadUserByUsername(passwordAuthToken.getPrincipal().toString());
        } catch (UsernameNotFoundException ex) {
            if (BeanUtils.isNotNull(authentication.getCredentials())) {
                String passwordProvided = ContextUtils.getAuthenticatedPassword(authentication);
                passwordEncoder.matches(passwordProvided, userNotFoundEncodedPassword);
            }
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }

        if (BeanUtils.isNull(loadedUserDetails)) {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, "
                                                             + "which is an interface contract violation");
        }

        return loadedUserDetails;
    }

}
