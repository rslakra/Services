package com.rslakra.springbootsamples.jwtauthentication.security.services;

import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.rslakra.springbootsamples.jwtauthentication.persistence.model.IdentityDO;
import com.rslakra.springbootsamples.jwtauthentication.persistence.repository.IdentityRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    // LOGGER
    private final static Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private IdentityRepository identityRepository;

    /**
     * @param userName
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        LOGGER.debug("+loadUserByUsername({})", userName);
        IdentityDO
            identity =
            identityRepository.findByUserName(userName)
                .orElseThrow(() -> new NoRecordFoundException("userName:%s", userName));
        final UserPrinciple userPrinciple = UserPrinciple.build(identity);
        LOGGER.debug("-loadUserByUsername(), userPrinciple:{}", userPrinciple);
        return userPrinciple;
    }
}
