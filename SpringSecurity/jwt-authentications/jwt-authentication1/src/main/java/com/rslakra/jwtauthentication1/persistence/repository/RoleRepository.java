package com.rslakra.jwtauthentication1.persistence.repository;

import com.rslakra.jwtauthentication1.persistence.model.Role;
import com.rslakra.jwtauthentication1.persistence.model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByName(Roles name);
}
