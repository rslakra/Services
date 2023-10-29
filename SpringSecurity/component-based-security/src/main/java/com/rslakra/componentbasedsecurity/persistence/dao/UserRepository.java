package com.rslakra.componentbasedsecurity.persistence.dao;

import com.rslakra.componentbasedsecurity.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUserName(String userName);

}
