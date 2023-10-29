package com.rslakra.springsecurity.domain.repository;

import com.rslakra.springsecurity.domain.entity.UserDetail;

public interface UserRepository {

    public UserDetail findUser(String username, String domain);
    
}
