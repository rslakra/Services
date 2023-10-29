package com.rslakra.springservices.thymeleafsidebar.task.service;

import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.rslakra.springservices.thymeleafsidebar.framework.service.AbstractService;
import com.rslakra.springservices.thymeleafsidebar.task.persistence.entity.Task;
import com.rslakra.springservices.thymeleafsidebar.task.persistence.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 1/27/23 11:43 AM
 */
@Service
public class TaskService extends AbstractService<Task, Long> {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskService(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%d", id));
    }

    /**
     * @return
     */
    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    /**
     * @param task
     * @return
     */
    @Override
    public Task create(Task task) {
        return taskRepository.save(task);
    }

    /**
     * @param task
     * @return
     */
    @Override
    public Task update(Task task) {
        return taskRepository.save(task);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Task delete(final Long id) {
        Task task = findById(id);
        taskRepository.deleteById(id);
        return task;
    }

    /**
     * @param name
     * @return
     */
    public List<Task> findByNameContainsIgnoreCase(String name) {
        return taskRepository.findByNameContainsIgnoreCase(name);
    }

    /**
     * @param id
     * @param isCompleted
     */
    public void updateTaskStatus(Long id, boolean isCompleted) {
        taskRepository.updateTaskStatus(id, isCompleted);
    }
}
