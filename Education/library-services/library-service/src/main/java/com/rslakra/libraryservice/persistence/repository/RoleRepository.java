package com.rslakra.libraryservice.persistence.repository;

import com.rslakra.libraryservice.persistence.entity.Role;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 10/15/21 1:52 PM
 */
@Repository
public interface RoleRepository extends BaseRepository<Role, Long> {

    /**
     * @param name
     * @return
     */
//    @Query("SELECT r FROM Role r WHERE r.name = :name")
    Optional<Role> findByName(@Param("name") String name);

    /**
     * @param status
     * @return
     */
//    @Query("SELECT r FROM Role r WHERE r.status = :status")
    List<Role> getByStatus(@Param("status") String status);

}
