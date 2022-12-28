package com.rslakra.springbootsamples.springbootsample.repository;

import com.rslakra.springbootsamples.springbootsample.domain.user.IdentityDO;
import com.rslakra.springbootsamples.springbootsample.service.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/12/21 3:22 PM
 */
@Repository
public interface IdentityRepository extends JpaRepository<IdentityDO, Long> {

    IdentityDO findByUserIdAndStatus(String username, UserStatus status);

    IdentityDO findByMailId(String mailId);

    IdentityDO findById(String id);

    @Modifying
    @Query("update Identity i set i.password = :password where i.id = :id")
    void updatePassword(@Param("id") String id, @Param("password") String password);

}
