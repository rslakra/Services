package com.rslakra.iws.taskservice.task.persistence.repository;

import com.devamatre.framework.spring.persistence.repository.BaseRepository;
import com.rslakra.iws.taskservice.task.persistence.entity.Todo;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 2/7/23 2:25 PM
 */
@Repository
public interface TodoRepository extends BaseRepository<Todo, Long> {

    /**
     * @param userId
     * @return
     */
    Optional<Todo> findByIdAndUserId(@Param("id") Long id, @Param("userId") Long userId);

    /**
     * @param userId
     * @return
     */
    List<Todo> findAllByUserId(@Param("userId") Long userId);
}
