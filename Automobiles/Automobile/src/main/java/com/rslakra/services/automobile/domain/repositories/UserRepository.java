package com.rslakra.services.automobile.domain.repositories;

import com.rslakra.services.automobile.domain.entities.AutoUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @since 09-16-2019 1:39:54 PM
 */
@Repository
public interface UserRepository extends JpaRepository<AutoUser, Long> {

    /**
     * Returns the max id of the object.
     *
     * @return
     */
    public Optional<AutoUser> findFirstByOrderByIdDesc();

    /**
     * Finds the user by <code>username</code>.
     *
     * @param email
     * @return
     */
    public Optional<AutoUser> findByEmail(String email);

}
