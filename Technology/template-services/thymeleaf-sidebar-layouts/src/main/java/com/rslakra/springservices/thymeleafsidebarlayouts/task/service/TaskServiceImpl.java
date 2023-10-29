package com.rslakra.springservices.thymeleafsidebarlayouts.task.service;

import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.rslakra.springservices.thymeleafsidebarlayouts.framework.service.AbstractServiceImpl;
import com.rslakra.springservices.thymeleafsidebarlayouts.task.persistence.entity.Task;
import com.rslakra.springservices.thymeleafsidebarlayouts.task.persistence.repository.TaskRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 1/27/23 11:43 AM
 */
@Service
public class TaskServiceImpl extends AbstractServiceImpl<Task> implements TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(final TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Task getById(final Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id: %d", id));
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param task
     * @return
     */
    @Override
    public Task validate(Task task) {
        return task;
    }

    /**
     * Creates the <code>T</code> object.
     *
     * @param task
     * @return
     */
    @Override
    public Task create(Task task) {
        task = taskRepository.save(task);
        return task;
    }

    /**
     * Creates the <code>List<T></code> objects.
     *
     * @param tasks
     * @return
     */
    @Override
    public List<Task> create(List<Task> tasks) {
        final List<Task> savedTasks = new ArrayList<>();
        tasks.forEach(task -> {
            try {
                savedTasks.add(create(task));
            } catch (Exception ex) {
                LOGGER.error(ex.getLocalizedMessage(), ex);
            }
        });

        return savedTasks;
    }

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    @Override
    public List<Task> getAll() {
        return taskRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @return
     */
    @Override
    public List<Task> getByFilter() {
        return taskRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Task> getByFilter(Pageable pageable) {
        return taskRepository.findAll(pageable);
    }

    /**
     * Updates the <code>T</code> object.
     *
     * @param task
     * @return
     */
    @Override
    public Task update(Task task) {
        Task oldTask = getById(task.getId());
        BeanUtils.copyProperties(task, oldTask);
        task = taskRepository.save(oldTask);
        return task;
    }

    /**
     * Updates the <code>List<T></code> objects.
     *
     * @param tasks
     * @return
     */
    @Override
    public List<Task> update(List<Task> tasks) {
        final List<Task> savedTasks = new ArrayList<>();
        tasks.forEach(task -> {
            try {
                savedTasks.add(update(task));
            } catch (Exception ex) {
                LOGGER.error(ex.getLocalizedMessage(), ex);
            }
        });

        return savedTasks;
    }

    /**
     * Deletes the object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Task delete(Long id) {
        Task task = getById(id);
        taskRepository.deleteById(id);
        return task;
    }

    @Override
    public List<Task> findByNameContainsIgnoreCase(String name) {
        return taskRepository.findByNameContainsIgnoreCase(name);
    }

    /**
     * @param id
     * @param isCompleted
     */
    @Override
    public void updateTaskStatus(Long id, boolean isCompleted) {
        taskRepository.updateTaskStatus(id, isCompleted);
    }

}
