package com.rslakra.componentbasedsecurity.service;

import com.rslakra.componentbasedsecurity.payload.dto.UserDTO;
import com.rslakra.componentbasedsecurity.persistence.dao.UserRepository;
import com.rslakra.componentbasedsecurity.persistence.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Autowired
    private UserRepository userRepository;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        LOGGER.debug("loadUserByUsername(" + username + ")");
        final User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
                                                                      new ArrayList<>());
    }

    /**
     * Saves the user.
     *
     * @param user
     * @return
     */
    public User save(UserDTO user) {
        LOGGER.debug("save(" + user + ")");
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(bcryptEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
}
