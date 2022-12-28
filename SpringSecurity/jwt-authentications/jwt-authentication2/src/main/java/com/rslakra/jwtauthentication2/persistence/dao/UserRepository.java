package com.rslakra.jwtauthentication2.persistence.dao;

import com.rslakra.jwtauthentication2.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

}
