package com.rslakra.springsecurity.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
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
    private UserDetailService userDetailsService;

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
    public UserDetailsAuthenticationProvider(PasswordEncoder passwordEncoder, UserDetailService userDetailsService) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails,
                                                  UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {
        LOGGER.debug("+additionalAuthenticationChecks({}, {})", userDetails, authentication);
        if (authentication.getCredentials() == null) {
            logger.debug("Authentication failed: no credentials provided");
            throw new BadCredentialsException(
                messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }

        String presentedPassword = authentication.getCredentials().toString();
        LOGGER.debug("presentedPassword: {}", presentedPassword);

        if (!passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
            logger.debug("Authentication failed: password does not match stored value");
            throw new BadCredentialsException(
                messages.getMessage("AbstractUserDetailsAuthenticationProvider.badCredentials", "Bad credentials"));
        }
        LOGGER.debug("-additionalAuthenticationChecks({}, {})", userDetails, authentication);
    }

    @Override
    protected void doAfterPropertiesSet() throws Exception {
        LOGGER.debug("+doAfterPropertiesSet()");
        Assert.notNull(this.userDetailsService, "A UserDetailsService must be set");
        this.userNotFoundEncodedPassword = this.passwordEncoder.encode(USER_NOT_FOUND_PASSWORD);
        LOGGER.debug("-doAfterPropertiesSet(), userNotFoundEncodedPassword: {}", this.userNotFoundEncodedPassword);
    }

    /**
     * @param username
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
        throws AuthenticationException {
        LOGGER.debug("+retrieveUser({}, {})", username, authentication);
        PasswordAuthenticationToken passwordAuthToken = (PasswordAuthenticationToken) authentication;
        UserDetails loadedUser;

        try {
            LOGGER.debug("passwordAuthToken: {}", passwordAuthToken);
            loadedUser = this.userDetailsService.loadUserByUsernameAndDomain(passwordAuthToken.getPrincipal()
                                                                                 .toString(),
                                                                             passwordAuthToken.getDomain());
        } catch (UsernameNotFoundException ex) {
            LOGGER.error(ex.getMessage(), ex);
            if (authentication.getCredentials() != null) {
                String presentedPassword = authentication.getCredentials()
                    .toString();
                passwordEncoder.matches(presentedPassword, userNotFoundEncodedPassword);
            }
            throw ex;
        } catch (Exception ex) {
            throw new InternalAuthenticationServiceException(ex.getMessage(), ex);
        }

        if (loadedUser == null) {
            throw new InternalAuthenticationServiceException("UserDetailsService returned null, "
                                                             + "which is an interface contract violation");
        }

        LOGGER.debug("-retrieveUser(), loadedUser: {}", loadedUser);
        return loadedUser;
    }

}
