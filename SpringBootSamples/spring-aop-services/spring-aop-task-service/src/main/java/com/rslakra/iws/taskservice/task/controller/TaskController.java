package com.rslakra.iws.taskservice.task.controller;

import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.controller.rest.AbstractRestController;
import com.devamatre.framework.spring.filter.Filter;
import com.rslakra.iws.taskservice.task.filter.TaskFilter;
import com.rslakra.iws.taskservice.task.persistence.entity.Task;
import com.rslakra.iws.taskservice.task.service.TaskService;
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
@RequestMapping("${restPrefix}/tasks")
public class TaskController extends AbstractRestController<Task, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);

    private final TaskService taskService;

    /**
     * @param taskService
     */
    @Autowired
    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @return
     */
    @GetMapping
    @Override
    public List<Task> getAll() {
        return taskService.getAll();
    }

    /**
     * Returns the list of <code>T</code> filters objects.
     *
     * @param allParams
     * @return
     */
    @GetMapping("/filter")
    @Override
    public List<Task> getByFilter(@RequestParam Map<String, Object> allParams) {
        LOGGER.debug("+getByFilter({})", allParams);
        List<Task> tasks = Collections.emptyList();
        TaskFilter taskFilter = new TaskFilter(allParams);
        if (taskFilter.hasKeys(TaskFilter.ID, TaskFilter.FIRST_NAME)) {
        } else if (taskFilter.hasKey(TaskFilter.ID)) {
            tasks = Arrays.asList(taskService.getById(taskFilter.getLong(TaskFilter.ID)));
        } else if (taskFilter.hasKey(TaskFilter.FIRST_NAME)) {
        } else {
            tasks = taskService.getAll();
        }

        LOGGER.debug("-getByFilter(), tasks: {}", tasks);
        return tasks;
    }

    /**
     * Returns the <code>Page<T></code> list of objects filtered with <code>allParams</code>.
     *
     * @param allParams @return
     * @param pageable
     */
    @GetMapping("/pageable")
    @Override
    public Page<Task> getByFilter(Map<String, Object> allParams, Pageable pageable) {
        return taskService.getByFilter(null, pageable);
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<Task> getByFilter(Filter filter) {
        return null;
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Task> getByFilter(Filter filter, Pageable pageable) {
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
    public ResponseEntity<Task> create(@Validated @RequestBody Task task) {
        task = taskService.create(task);
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
    public ResponseEntity<List<Task>> create(@Validated @RequestBody List<Task> tasks) {
        tasks = taskService.create(tasks);
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
    public ResponseEntity<Task> update(@Validated @RequestBody Task task) {
        task = taskService.update(task);
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
    public ResponseEntity<List<Task>> update(@Validated @RequestBody List<Task> tasks) {
        tasks = taskService.update(tasks);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Deletes the <code>T</code> type object by <code>id</code>.
     *
     * @param idOptional
     * @return
     */
    @DeleteMapping("/{taskId}")
    @Override
    public ResponseEntity<Payload> delete(@PathVariable(value = "taskId") Optional<Long> idOptional) {
        validate(idOptional);
        taskService.delete(idOptional.get());
        Payload<String, Object> payload = Payload.newBuilder()
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
