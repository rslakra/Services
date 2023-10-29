package com.rslakra.iws.taskservice.task.controller;

import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.controller.rest.AbstractRestController;
import com.devamatre.framework.spring.filter.Filter;
import com.rslakra.iws.taskservice.task.filter.TodoFilter;
import com.rslakra.iws.taskservice.task.persistence.entity.Todo;
import com.rslakra.iws.taskservice.task.service.TodoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/25/22 5:08 PM
 */
@RestController
@RequestMapping("${restPrefix}/todos")
public class TodoController extends AbstractRestController<Todo, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TodoController.class);

    private final TodoService todoService;

    /**
     * @param todoService
     */
    @Autowired
    public TodoController(final TodoService todoService) {
        this.todoService = todoService;
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @return
     */
    @GetMapping
    @Override
    public List<Todo> getAll() {
        return todoService.getAll();
    }

    /**
     * Returns the list of <code>T</code> filters objects.
     *
     * @param allParams
     * @return
     */
    @GetMapping("/filter")
    @Override
    public List<Todo> getByFilter(@RequestParam Map<String, Object> allParams) {
        LOGGER.debug("+getByFilter({})", allParams);
        List<Todo> todos = Collections.emptyList();
        TodoFilter todoFilter = new TodoFilter(allParams);
        if (todoFilter.hasKeys(TodoFilter.ID, TodoFilter.FIRST_NAME)) {
        } else if (todoFilter.hasKey(TodoFilter.ID)) {
            todos = Arrays.asList(todoService.getById(todoFilter.getLong(TodoFilter.ID)));
        } else {
            todos = todoService.getAll();
        }

        LOGGER.debug("-getByFilter(), todos: {}", todos);
        return todos;
    }

    /**
     * Returns the <code>Page<T></code> list of objects filtered with <code>allParams</code>.
     *
     * @param allParams @return
     * @param pageable
     */
    @GetMapping("/pageable")
    @Override
    public Page<Todo> getByFilter(Map<String, Object> allParams, Pageable pageable) {
        return todoService.getByFilter(null, pageable);
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<Todo> getByFilter(Filter filter) {
        return null;
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Todo> getByFilter(Filter filter, Pageable pageable) {
        return null;
    }

    /**
     * Creates the <code>T</code> type object.
     *
     * @param task
     * @return
     */
    @PostMapping
    @ResponseBody
    @Override
    public ResponseEntity<Todo> create(@Validated @RequestBody Todo task) {
        task = todoService.create(task);
        return ResponseEntity.ok(task);
    }

    /**
     * Creates the list of <code>T</code> type objects.
     *
     * @param tasks
     * @return
     */
    @PostMapping("/batch")
    @ResponseBody
    @Override
    public ResponseEntity<List<Todo>> create(@Validated @RequestBody List<Todo> tasks) {
        tasks = todoService.create(tasks);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Updates the <code>T</code> type object.
     *
     * @param task
     * @return
     */
    @PutMapping
    @Override
    public ResponseEntity<Todo> update(@Validated @RequestBody Todo task) {
        task = todoService.update(task);
        return ResponseEntity.ok(task);
    }

    /**
     * Updates the list of <code>T</code> type objects.
     *
     * @param tasks
     * @return
     */
    @PutMapping("/batch")
    @Override
    public ResponseEntity<List<Todo>> update(@Validated @RequestBody List<Todo> tasks) {
        tasks = todoService.update(tasks);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Deletes the <code>T</code> type object by <code>id</code>.
     *
     * @param idOptional
     * @return
     */
    @DeleteMapping("/{todoId}")
    @Override
    public ResponseEntity<Payload> delete(@PathVariable(value = "todoId") Optional<Long> idOptional) {
        validate(idOptional);
        todoService.delete(idOptional.get());
        Payload payload = Payload.newBuilder()
            .withDeleted(Boolean.TRUE)
            .withMessage("Record with id:%d deleted successfully!", idOptional.get());
        return ResponseEntity.ok(payload);
    }

    /**
     * Uploads the <code>file</code>
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @Override
    public ResponseEntity<Payload> upload(MultipartFile file) {
        return null;
    }

    /**
     * Downloads the object of <code>T</code> as <code>fileType</code> file.
     *
     * @param fileType
     * @return
     */
    @GetMapping("/download")
    @Override
    public ResponseEntity<Resource> download(String fileType) {
        return null;
    }

}
