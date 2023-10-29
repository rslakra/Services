package com.rslakra.iws.businessservice.task.controller.web;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.frameworks.spring.controller.web.AbstractWebController;
import com.rslakra.frameworks.spring.filter.Filter;
import com.rslakra.frameworks.spring.parser.Parser;
import com.rslakra.iws.businessservice.task.persistence.entity.Task;
import com.rslakra.iws.businessservice.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

/**
 * @author: Rohtash Lakra
 * @since: 9/30/19 5:38 PM
 */
@Controller
@RequestMapping("/tasks")
public class TaskWebController extends AbstractWebController<Task> {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskWebController.class);

    // taskService
    private final TaskService taskService;

    /**
     * @param taskService
     */
    @Autowired
    public TaskWebController(TaskService taskService) {
        LOGGER.debug("TaskWebController({})", taskService);
        this.taskService = taskService;
    }

    /**
     * @param id
     */
    @Override
    public void validate(Optional<Long> id) {
        super.validate(id);
    }

    /**
     * Saves the <code>t</code> object.
     *
     * @param task
     * @return
     */
    @PostMapping("/save")
    @Override
    public String save(Task task) {
        LOGGER.debug("save({})", task);
        if (BeanUtils.isNotNull(task.getId())) {
            Task oldTask = taskService.getById(task.getId());
            BeanUtils.copyProperties(task, oldTask);
            task = taskService.update(oldTask);
        } else {
            task = taskService.create(task);
        }

        return "redirect:/tasks/list";
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @param model
     * @return
     */
    @GetMapping("/list")
    @Override
    public String getAll(Model model) {
        List<Task> tasks = taskService.getAll();
        model.addAttribute("tasks", tasks);
        return "views/task/listTasks";
    }

    /**
     * Filters the list of <code>T</code> objects.
     *
     * @param model
     * @param filter
     * @return
     */
    @GetMapping("/filter")
    @Override
    public String filter(Model model, Filter filter) {
        List<Task> tasks = taskService.getAll();
        model.addAttribute("tasks", tasks);
        return "views/task/listTasks";
    }

    /**
     * @param model
     * @param taskId
     * @return
     */
    @RequestMapping(path = {"/create", "/update/{taskId}"})
    public String editObject(Model model, @PathVariable(name = "taskId") Optional<Long> taskId) {
        Task task = null;
        if (taskId.isPresent()) {
            task = taskService.getById(taskId.get());
        } else {
            task = new Task();
        }
        model.addAttribute("task", task);

        return "views/task/editTask";
    }

    /**
     * Deletes the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/delete/{taskId}")
    @Override
    public String delete(Model model, @PathVariable(name = "taskId") Optional<Long> id) {
        validate(id);
        taskService.delete(id.get());
        return "redirect:/tasks/list";
    }

    /**
     * @return
     */
    @Override
    public Parser<Task> getParser() {
        return null;
    }
}
