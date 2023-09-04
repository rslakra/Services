package com.rslakra.springsecurity.domain.repository;

import java.util.ArrayList;
import java.util.Collection;

import com.rslakra.springsecurity.domain.entity.UserDetail;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    private PasswordEncoder passwordEncoder;
    
    public UserRepositoryImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetail findUser(String username, String domain) {
        if (StringUtils.isAnyBlank(username, domain)) {
            return null;
        } else {
            Collection<? extends GrantedAuthority> authorities = new ArrayList<>();
            UserDetail userDetail =  new UserDetail(username, domain,
                                                    passwordEncoder.encode("secret"), true,
                                                    true, true, true, authorities);
            return userDetail;
        }
    }

}
