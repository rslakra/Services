package com.rslakra.liquibaseservice.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 10/20/22 11:27 AM
 */
@NoRepositoryBean
public interface BaseRepository<E, ID> extends JpaRepository<E, ID> {

    /**
     * Returns an entity of type <code>E</code> for the given <code>ID</code>
     *
     * @param id
     * @return
     */
    Optional<E> findById(ID id);

}
