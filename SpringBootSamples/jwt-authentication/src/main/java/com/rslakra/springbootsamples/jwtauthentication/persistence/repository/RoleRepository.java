package com.rslakra.springbootsamples.jwtauthentication.persistence.repository;

import com.rslakra.springbootsamples.jwtauthentication.persistence.model.RoleDO;
import com.rslakra.springbootsamples.jwtauthentication.persistence.model.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<RoleDO, Long> {

    Optional<RoleDO> findByName(RoleType roleName);
}
