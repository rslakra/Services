package com.rslakra.springbootsamples.emailservice.service.impl;

import com.rslakra.springbootsamples.emailservice.domain.user.Identity;
import com.rslakra.springbootsamples.emailservice.dto.UserRequest;
import com.rslakra.springbootsamples.emailservice.repository.IdentityRepository;
import com.rslakra.springbootsamples.emailservice.service.UserService;
import com.rslakra.springbootsamples.emailservice.service.UserStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Rohtash Lakra
 * @created 1/6/22 4:21 PM
 */
@Service
public class UserServiceImpl implements UserService {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private IdentityRepository identityRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * @param userRequest
     * @return
     */
    public String saveUser(UserRequest userRequest) {
        Identity identity = new Identity();
        identity.setFirstName(userRequest.getFirstName());
        identity.setLastName(userRequest.getLastName());
        identity.setEmail(userRequest.getEmail());
        identity.setPhoneNumber(userRequest.getPhoneNumber());
        identity.setId(userRequest.getPhoneNumber());
        identity.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        identity.setStatus(UserStatus.ACTIVE);
        identityRepository.save(identity);
        LOGGER.info("Saving user with userId:{}", identity.getId());
        return identity.getId();
    }

    public void updatePassword(String userId, String password) {
        identityRepository.updatePassword(userId, password);
    }

}
