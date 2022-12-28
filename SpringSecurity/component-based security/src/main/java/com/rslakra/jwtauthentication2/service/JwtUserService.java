package com.rslakra.jwtauthentication2.service;

import com.rslakra.jwtauthentication2.persistence.dao.UserRepository;
import com.rslakra.jwtauthentication2.persistence.model.User;
import com.rslakra.jwtauthentication2.payload.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service(value = "jwtUserService")
public class JwtUserService implements UserDetailsService {

    private static Logger LOGGER = LoggerFactory.getLogger(JwtUserService.class);

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
