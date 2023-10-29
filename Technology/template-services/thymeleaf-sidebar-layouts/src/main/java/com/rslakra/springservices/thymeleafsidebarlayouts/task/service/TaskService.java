package com.rslakra.springservices.thymeleafsidebarlayouts.task.service;

import com.rslakra.springservices.thymeleafsidebarlayouts.framework.service.AbstractService;
import com.rslakra.springservices.thymeleafsidebarlayouts.task.persistence.entity.Task;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 1/27/23 11:43 AM
 */
public interface TaskService extends AbstractService<Task> {

    public List<Task> findByNameContainsIgnoreCase(String name);

    /**
     * @param id
     * @param isCompleted
     */
    public void updateTaskStatus(Long id, boolean isCompleted);
}
