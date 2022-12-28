package com.rslakra.jwtauthentication3.persistence.repository;

import com.rslakra.jwtauthentication3.persistence.model.Role;
import com.rslakra.jwtauthentication3.persistence.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(RoleName roleName);
}
