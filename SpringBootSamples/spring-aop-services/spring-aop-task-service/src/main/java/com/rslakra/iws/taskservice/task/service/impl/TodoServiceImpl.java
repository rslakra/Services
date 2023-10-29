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
import com.rslakra.iws.taskservice.task.persistence.entity.Todo;
import com.rslakra.iws.taskservice.task.persistence.repository.TodoRepository;
import com.rslakra.iws.taskservice.task.service.TodoService;
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
public class TodoServiceImpl extends AbstractServiceImpl<Todo, Long> implements TodoService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoServiceImpl.class);

    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    /**
     * @param todoRepository
     */
    @Autowired
    public TodoServiceImpl(final UserRepository userRepository, final TodoRepository todoRepository) {
        LOGGER.debug("TodoServiceImpl({}, {})", userRepository, todoRepository);
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param todo
     * @return
     */
    @Override
    public Todo validate(Operation operation, Todo todo) {
        LOGGER.debug("+validate({}, {})", operation, todo);
        switch (operation) {
            case CREATE: {
                if (BeanUtils.isEmpty(todo.getName())) {
                    throw new InvalidRequestException("The todo's name should provide!");
                } else if (BeanUtils.isNull(todo.getUserId())) {
                    throw new InvalidRequestException("The todo's userId should provide!");
                }

                // validate the user exists
                Optional<User> userOptional = userRepository.findById(todo.getUserId());
                if (!userOptional.isPresent()) {
                    throw new InvalidRequestException("Invalid userId: %d", todo.getUserId());
                }

                // check task exists for this user
                List<Todo> todoList = todoRepository.findAllByUserId(todo.getUserId());
                if (todoList.stream().anyMatch(oldTodo -> oldTodo.getName().equals(todo.getName()))) {
                    throw new DuplicateRecordException("todo:%s for userId:%d", todo.getName(), todo.getUserId());
                }
            }

            break;

            case UPDATE: {
                if (BeanUtils.isNull(todo.getId())) {
                    throw new InvalidRequestException("The todo's ID should provide!");
                }

                if (BeanUtils.isNotEmpty(todo.getUserId())) {
                    Optional<Todo> todoOptional = todoRepository.findByIdAndUserId(todo.getId(), todo.getUserId());
                    if (!todoOptional.isPresent()) {
                        throw new InvalidRequestException("The todo does not belong to user: %d", todo.getUserId());
                    }
                }
            }

            break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        LOGGER.debug("-validate(), todo: {}", todo);
        return todo;
    }

    /**
     * Creates the <code>T</code> object.
     *
     * @param todo
     * @return
     */
    @Override
    public Todo create(Todo todo) {
        LOGGER.debug("+create({})", todo);
        todo = validate(Operation.CREATE, todo);
        todo = todoRepository.save(todo);
        LOGGER.debug("-create(), todo: {}", todo);
        return todo;
    }

    /**
     * Creates the <code>List<T></code> objects.
     *
     * @param todos
     * @return
     */
    @Override
    public List<Todo> create(List<Todo> todos) {
        LOGGER.debug("+create({})", todos);
        if (BeanUtils.isEmpty(todos)) {
            throw new InvalidRequestException("The todo should provide!");
        }

        todos.forEach(todo -> validate(Operation.CREATE, todo));
        todos = todoRepository.saveAll(todos);

        LOGGER.debug("-create(), todos: {}", todos);
        return todos;
    }

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    @Override
    public List<Todo> getAll() {
        return todoRepository.findAll();
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Todo getById(final Long id) {
        return todoRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%d", id));
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @return
     */
    @Override
    public List<Todo> getByFilter(Filter filter) {
        return todoRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Todo> getByFilter(Filter filter, Pageable pageable) {
        return todoRepository.findAll(pageable);
    }

    /**
     * Updates the <code>T</code> object.
     *
     * @param todo
     * @return
     */
    @Override
    public Todo update(Todo todo) {
        LOGGER.debug("+update({})", todo);
        todo = validate(Operation.UPDATE, todo);
        todo = todoRepository.save(todo);
        LOGGER.debug("-update(), todo: {}", todo);
        return todo;
    }

    /**
     * Updates the <code>List<T></code> objects.
     *
     * @param todos
     * @return
     */
    @Override
    public List<Todo> update(List<Todo> todos) {
        LOGGER.debug("+update({})", todos);
        if (BeanUtils.isEmpty(todos)) {
            throw new InvalidRequestException("The todos should provide!");
        }

        todos.forEach(todo -> validate(Operation.UPDATE, todo));
        todos = todoRepository.saveAll(todos);

        LOGGER.debug("-update(), todos: {}", todos);
        return todos;
    }

    /**
     * Deletes the object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Todo delete(Long id) {
        LOGGER.debug("+delete({})", id);
        BeanUtils.assertNonNull(id, "TODO ID should provide!");
        Todo todo = getById(id);
        LOGGER.info("Deleting {}", todo);
        todoRepository.delete(todo);
        LOGGER.debug("-delete(), todo: {}", todo);
        return todo;
    }
}
