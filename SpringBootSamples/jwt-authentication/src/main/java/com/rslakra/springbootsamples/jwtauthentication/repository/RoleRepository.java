package com.rslakra.springbootsamples.jwtauthentication.repository;

import com.rslakra.springbootsamples.jwtauthentication.model.RoleDO;
import com.rslakra.springbootsamples.jwtauthentication.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleDO, Long> {

    Optional<RoleDO> findByName(RoleType roleName);
}
