package com.rslakra.jwtauthentication7.service.security;

import com.rslakra.jwtauthentication7.persistence.repository.UserRepository;
import com.rslakra.jwtauthentication7.persistence.domain.User;
import com.rslakra.jwtauthentication7.model.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service(value = "userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    /**
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        LOGGER.debug("loadUserByUsername({})", username);
        final User user = userRepository.findByUserName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }

//        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
//                                                                      new ArrayList<>());
        return ContextUser.build(user);
    }

    /**
     * Saves the user.
     *
     * @param user
     * @return
     */
    public User save(UserDTO user) {
        LOGGER.debug("save({})", user);
        User newUser = new User();
        newUser.setUserName(user.getUserName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(newUser);
    }
}
