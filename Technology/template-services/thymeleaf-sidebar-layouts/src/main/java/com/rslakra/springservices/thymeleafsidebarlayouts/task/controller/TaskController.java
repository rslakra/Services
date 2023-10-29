package com.rslakra.springservices.thymeleafsidebarlayouts.task.controller;

import com.rslakra.springservices.thymeleafsidebarlayouts.task.persistence.entity.Task;
import com.rslakra.springservices.thymeleafsidebarlayouts.task.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping(value = "${apiPrefix}/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskController {

    private final TaskService taskService;

    /**
     * @param taskService
     */
    @Autowired
    public TaskController(final TaskService taskService) {
        this.taskService = taskService;
    }

    /**
     * @param task
     * @return
     */
    @PostMapping
    public Task create(@RequestBody Task task) {
        task = taskService.create(task);
        return task;
    }

    /**
     * @param tasks
     * @return
     */
    @PostMapping("/batch")
    public List<Task> batchCreate(@RequestBody List<Task> tasks) {
        tasks = taskService.create(tasks);
        return tasks;
    }

    /**
     * @return
     */
    @GetMapping
    public List<Task> getAll() {
        return taskService.getAll();
    }

    /**
     * @param keyword
     * @return
     */
    @GetMapping("/filter")
    public List<Task> getByFilter(@Param("keyword") String keyword) {
        final List<Task> tasks = new ArrayList<>();
        if (keyword == null) {
            taskService.getAll().forEach(tasks::add);
        } else {
            taskService.findByNameContainsIgnoreCase(keyword).forEach(tasks::add);
        }

        return tasks;
    }

    /**
     * @param task
     * @return
     */
    @PutMapping
    public Task update(@RequestBody Task task) {
        task = taskService.update(task);
        return task;
    }

    /**
     * @param tasks
     * @return
     */
    @PutMapping("/batch")
    public List<Task> batchUpdate(@RequestBody List<Task> tasks) {
        tasks = taskService.update(tasks);
        return tasks;
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Task delete(@PathVariable("id") Long id) {
        Task task = taskService.getById(id);
        taskService.delete(id);
        return task;
    }

}
