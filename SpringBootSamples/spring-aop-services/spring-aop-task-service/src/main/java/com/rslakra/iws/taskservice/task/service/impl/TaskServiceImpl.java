package com.rslakra.iws.taskservice.task.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.exception.DuplicateRecordException;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.persistence.Operation;
import com.devamatre.framework.spring.service.AbstractServiceImpl;
import com.rslakra.iws.taskservice.account.persistence.entity.User;
import com.rslakra.iws.taskservice.account.persistence.repository.UserRepository;
import com.rslakra.iws.taskservice.task.persistence.entity.Task;
import com.rslakra.iws.taskservice.task.persistence.repository.TaskRepository;
import com.rslakra.iws.taskservice.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 10:06 AM
 */
@Service
public class TaskServiceImpl extends AbstractServiceImpl<Task, Long> implements TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskServiceImpl.class);

    private final UserRepository userRepository;
    private final TaskRepository taskRepository;

    /**
     * @param taskRepository
     */
    @Autowired
    public TaskServiceImpl(final UserRepository userRepository, final TaskRepository taskRepository) {
        LOGGER.debug("TaskServiceImpl({}, {})", userRepository, taskRepository);
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param task
     * @return
     */
    @Override
    public Task validate(Operation operation, Task task) {
        LOGGER.debug("+validate({}, {})", operation, task);
        switch (operation) {
            case CREATE: {
                if (BeanUtils.isEmpty(task.getTitle())) {
                    throw new InvalidRequestException("The task's title should provide!");
                } else if (BeanUtils.isEmpty(task.getDescription())) {
                    throw new InvalidRequestException("The task's description should provide!");
                } else if (BeanUtils.isEmpty(task.getPriority())) {
                    throw new InvalidRequestException("The task's priority should provide!");
                } else if (BeanUtils.isNull(task.getUserId())) {
                    throw new InvalidRequestException("The task's userId should provide!");
                }

                // validate the user exists
                Optional<User> userOptional = userRepository.findById(task.getUserId());
                if (!userOptional.isPresent()) {
                    throw new InvalidRequestException("Invalid userId: %d", task.getUserId());
                }

                // check task exists for this user
                List<Task> taskList = taskRepository.findAllByUserId(task.getUserId());
                if (taskList.stream().anyMatch(oldTask -> oldTask.getTitle().equals(task.getTitle()))) {
                    throw new DuplicateRecordException("task:%s for userId:%d", task.getTitle(), task.getUserId());
                }
            }

            break;

            case UPDATE: {
                if (BeanUtils.isNull(task.getId())) {
                    throw new InvalidRequestException("The task's ID should provide!");
                }

//                Task oldTask = getById(task.getId());
                if (BeanUtils.isNotEmpty(task.getUserId())) {
                    // loads all tasks of the user
//                    final List<Task> userTasks = taskRepository.findAllByUserId(task.getUserId());
//                    if (!userTasks.stream().anyMatch(userTask -> userTask.getId().equals(oldTask.getId()))) {
//                        throw new InvalidRequestException("The task does not belong to user: %d", task.getUserId());
//                    }
                    Optional<Task> taskOptional = taskRepository.findByIdAndUserId(task.getId(), task.getUserId());
                    if (!taskOptional.isPresent()) {
                        throw new InvalidRequestException("The task does not belong to user: %d", task.getUserId());
                    }
                }
            }

            break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        LOGGER.debug("-validate(), task: {}", task);
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
        LOGGER.debug("+create({})", task);
        task = validate(Operation.CREATE, task);
        task = taskRepository.save(task);
        LOGGER.debug("-create(), task: {}", task);
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
        LOGGER.debug("+create({})", tasks);
        if (BeanUtils.isEmpty(tasks)) {
            throw new InvalidRequestException("The tasks should provide!");
        }

        tasks.forEach(task -> validate(Operation.CREATE, task));
        tasks = taskRepository.saveAll(tasks);

        LOGGER.debug("-create(), tasks: {}", tasks);
        return tasks;
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
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Task getById(final Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%d", id));
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @return
     */
    @Override
    public List<Task> getByFilter(Filter filter) {
        return taskRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Task> getByFilter(Filter filter, Pageable pageable) {
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
        LOGGER.debug("+update({})", task);
        task = validate(Operation.UPDATE, task);
        task = taskRepository.save(task);
        LOGGER.debug("-update(), task: {}", task);
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
        LOGGER.debug("+update({})", tasks);
        if (BeanUtils.isEmpty(tasks)) {
            throw new InvalidRequestException("The artists should provide!");
        }

        tasks.forEach(task -> validate(Operation.UPDATE, task));
        tasks = taskRepository.saveAll(tasks);
        LOGGER.debug("-update(), task: {}", tasks);
        return tasks;
    }

    /**
     * Deletes the object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Task delete(Long id) {
        LOGGER.debug("+delete({})", id);
        BeanUtils.assertNonNull(id, "Task ID should provide!");
        Task task = getById(id);
        LOGGER.info("Deleting {}", task);
        taskRepository.delete(task);
        LOGGER.debug("-delete(), task: {}", task);
        return task;
    }
}
