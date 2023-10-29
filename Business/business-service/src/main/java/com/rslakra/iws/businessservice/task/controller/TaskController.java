package com.rslakra.iws.businessservice.task.controller;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.frameworks.core.Payload;
import com.rslakra.frameworks.spring.controller.rest.AbstractRestController;
import com.rslakra.frameworks.spring.parser.Parser;
import com.rslakra.frameworks.spring.parser.csv.CsvParser;
import com.rslakra.frameworks.spring.parser.excel.ExcelParser;
import com.rslakra.iws.businessservice.task.filter.TaskFilter;
import com.rslakra.iws.businessservice.task.parser.TaskParser;
import com.rslakra.iws.businessservice.task.persistence.entity.Task;
import com.rslakra.iws.businessservice.task.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
import java.util.Objects;
import java.util.Optional;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/25/22 5:08 PM
 */
@RestController
@RequestMapping("${restPrefix}/tasks")
public class TaskController extends AbstractRestController<Task> {

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
    public List<Task> getByFilter(@RequestParam Map<String, String> allParams) {
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
    public Page<Task> getByFilter(Map<String, String> allParams, Pageable pageable) {
        return taskService.getByFilter(null, pageable);
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
     * Uploads the <code>file</code> of objects.
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @Override
    public ResponseEntity<Payload> upload(@RequestParam("file") MultipartFile file) {
        Payload payload = Payload.newBuilder();
        try {
            List<Task> taskList = null;
            TaskParser taskParser = new TaskParser();
            if (CsvParser.isCSVFile(file)) {
                taskList = taskParser.readCSVStream(file.getInputStream());
            } else if (ExcelParser.isExcelFile(file)) {
                taskList = taskParser.readStream(file.getInputStream());
            }

            // check the task list is available
            if (Objects.nonNull(taskList)) {
                taskList = taskService.create(taskList);
                LOGGER.debug("taskList: {}", taskList);
                payload.withMessage("Uploaded the file '%s' successfully!", file.getOriginalFilename());
                return ResponseEntity.status(HttpStatus.OK).body(payload);
            }
        } catch (Exception ex) {
            LOGGER.error("Could not upload the file:{}!", file.getOriginalFilename(), ex);
            payload.withMessage("Could not upload the file '%s'!", file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(payload);
        }

        payload.withMessage("Unsupported file type!");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
    }

    /**
     * Downloads the object of <code>T</code> as <code>fileType</code> file.
     *
     * @param fileType
     * @return
     */
    @GetMapping("/download")
    @Override
    public ResponseEntity<Resource> download(@RequestParam("fileType") String fileType) {
        BeanUtils.assertNonNull(fileType, "Download 'fileType' must provide!");
        ResponseEntity responseEntity = null;
        InputStreamResource inputStreamResource = null;
        String contentDisposition;
        MediaType mediaType;
        TaskParser taskParser = new TaskParser();
        if (CsvParser.isCSVFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(TaskParser.CSV_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(CsvParser.CSV_MEDIA_TYPE);
            inputStreamResource = taskParser.buildCSVResourceStream(taskService.getAll());
        } else if (ExcelParser.isExcelFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(TaskParser.EXCEL_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(ExcelParser.EXCEL_MEDIA_TYPE);
            inputStreamResource = taskParser.buildStreamResources(taskService.getAll());
        } else {
            throw new UnsupportedOperationException("Unsupported fileType:" + fileType);
        }

        // check inputStreamResource is not null
        if (Objects.nonNull(inputStreamResource)) {
            responseEntity = Parser.buildOKResponse(contentDisposition, mediaType, inputStreamResource);
        }

        return responseEntity;
    }
}
