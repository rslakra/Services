package com.rslakra.springsecurity.service;

import com.rslakra.springsecurity.domain.entity.UserDetail;
import com.rslakra.springsecurity.domain.repository.UserRepository;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service("userDetailsService")
public class UserDetailServiceImpl implements UserDetailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserDetailServiceImpl.class);

    private UserRepository userRepository;

    /**
     * @param userRepository
     */
    public UserDetailServiceImpl(UserRepository userRepository) {
        LOGGER.debug("UserDetailServiceImpl({})", userRepository);
        this.userRepository = userRepository;
    }

    /**
     * @param username
     * @param domain
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsernameAndDomain(String username, String domain) throws UsernameNotFoundException {
        LOGGER.debug("+loadUserByUsernameAndDomain({}, {})", username, domain);
        if (StringUtils.isAnyBlank(username, domain)) {
            throw new UsernameNotFoundException("Username and domain must be provided");
        }

        UserDetail userDetail;
        if ("rlakra".equals(username)) {
            // test = $2a$10$ewE/Ij6hx6igF5lpa88ONe63sKGvQ58BAAvY4FevbilW/FOnLI8m2
            userDetail =
                new UserDetail("rlakra", "$2a$10$ewE/Ij6hx6igF5lpa88ONe63sKGvQ58BAAvY4FevbilW/FOnLI8m2",
                               new ArrayList<>());
        } else {
            userDetail = userRepository.findUser(username, domain);
        }

        if (userDetail == null) {
            throw new UsernameNotFoundException(
                String.format("No user found with username=%s, domain=%s", username, domain));
        }

        return userDetail;
    }
}
