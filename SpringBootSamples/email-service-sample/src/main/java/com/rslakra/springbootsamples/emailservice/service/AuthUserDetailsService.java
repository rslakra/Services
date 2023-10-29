package com.rslakra.springbootsamples.emailservice.service;

import com.rslakra.springbootsamples.emailservice.domain.user.IdentityDO;
import com.rslakra.springbootsamples.emailservice.repository.IdentityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:27 PM
 */
@Service
public class AuthUserDetailsService implements UserDetailsService {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthUserDetailsService.class);

    @Autowired
    private IdentityRepository usersRepository = null;

    public AuthUserDetailsService() {
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        IdentityDO users = usersRepository.findByUserIdAndStatus(username, UserStatus.ACTIVE);

        if (users == null) {
            LOGGER.info("User does not exists");
            throw new UsernameNotFoundException(username);
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority("ROLE_USER"));

        LoggedInUser loggedInUser = new LoggedInUser(users.getId(), users.getPassword(), grantedAuthorities);
        loggedInUser.setUserObjectId(users.getId());
        return loggedInUser;
    }

}
