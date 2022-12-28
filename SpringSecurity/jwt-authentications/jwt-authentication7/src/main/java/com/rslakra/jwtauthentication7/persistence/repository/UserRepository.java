package com.rslakra.jwtauthentication7.persistence.repository;

import com.rslakra.jwtauthentication7.persistence.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

}
