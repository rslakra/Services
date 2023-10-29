package com.rslakra.springsecurity.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailService {

    UserDetails loadUserByUsernameAndDomain(String username, String domain) throws UsernameNotFoundException;

}
