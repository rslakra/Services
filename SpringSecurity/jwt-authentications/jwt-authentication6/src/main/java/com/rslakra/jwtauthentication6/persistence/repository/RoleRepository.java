package com.rslakra.jwtauthentication6.persistence.repository;

import com.rslakra.jwtauthentication6.persistence.models.Role;
import com.rslakra.jwtauthentication6.persistence.models.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(Roles name);
}
