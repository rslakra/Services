package com.rslakra.iws.taskservice.account.controller;

import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.controller.rest.AbstractRestController;
import com.devamatre.framework.spring.filter.Filter;
import com.rslakra.iws.taskservice.account.filter.UserFilter;
import com.rslakra.iws.taskservice.account.persistence.entity.User;
import com.rslakra.iws.taskservice.account.service.UserService;
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
 * @author Rohtash Lakra
 * @created 8/5/21 11:11 AM
 */
@RestController
@RequestMapping("${restPrefix}/users")
//@Tag(name = "User Service")
public class UserController extends AbstractRestController<User, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    // @Resource
    private final UserService userService;

    /**
     * @param userService
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @return
     */
    @GetMapping
//    @Operation(summary = "Get all users", description = "Get all users",
//        tags = {"User Service"},
//        responses = {
//            @ApiResponse(responseCode = "200", description = "Get the users successfully", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))))
//        })
    @Override
    public List<User> getAll() {
        return userService.getAll();
    }

    /**
     * Returns the list of <code>T</code> filters objects.
     *
     * @param allParams
     * @return
     */
    @GetMapping("/filter")
    @Override
    public List<User> getByFilter(@RequestParam Map<String, Object> allParams) {
        LOGGER.debug("+getByFilter({})", allParams);
        List<User> users = Collections.emptyList();
        UserFilter userFilter = new UserFilter(allParams);
        if (userFilter.hasKeys(UserFilter.EMAIL, UserFilter.FIRST_NAME, UserFilter.LAST_NAME)) {
        } else if (userFilter.hasKeys(UserFilter.FIRST_NAME, UserFilter.LAST_NAME)) {
        } else if (userFilter.hasKey(UserFilter.ID)) {
            users = Arrays.asList(userService.getById(userFilter.getLong(UserFilter.ID)));
        } else if (userFilter.hasKey(UserFilter.EMAIL)) {
            users = Arrays.asList(userService.getByEmail(userFilter.getValue(UserFilter.EMAIL, String.class
            )));
        } else if (userFilter.hasKey(UserFilter.FIRST_NAME)) {
            users = userService.getByFirstName(userFilter.getValue(UserFilter.FIRST_NAME, String.class));
        } else if (userFilter.hasKey(UserFilter.LAST_NAME)) {
            users = userService.getByLastName(userFilter.getValue(UserFilter.LAST_NAME, String.class));
        } else {
            users = userService.getAll();
        }

        LOGGER.debug("-getByFilter(), users: {}", users);
        return users;
    }

    /**
     * Returns the <code>Page<T></code> list of objects filtered with <code>allParams</code>.
     *
     * @param allParams @return
     * @param pageable
     */
    @GetMapping("/pageable")
    @Override
    public Page<User> getByFilter(@RequestParam Map<String, Object> allParams, Pageable pageable) {
        return userService.getByFilter(null, pageable);
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<User> getByFilter(Filter filter) {
        return null;
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<User> getByFilter(Filter filter, Pageable pageable) {
        return null;
    }

    /**
     * Creates the <code>T</code> type object.
     *
     * @param user
     * @return
     */
    @PostMapping
    @ResponseBody
//    @Operation(summary = "Create new user", description = "Create new user",
//        tags = {"User Service"},
//        responses = {
//            @ApiResponse(responseCode = "200", description = "Creates the user successfully", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))))
//        })
    @Override
    public ResponseEntity<User> create(
//        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Create new user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)), required = true)
        @Validated @RequestBody User user) {
        user = userService.create(user);
        return ResponseEntity.ok(user);
    }

    /**
     * Creates the list of <code>T</code> type objects.
     *
     * @param users
     * @return
     */
    @PostMapping("/batch")
    @ResponseBody
//    @Operation(summary = "Create new user", description = "Create new user",
//        tags = {"User Service"},
//        responses = {
//            @ApiResponse(responseCode = "200", description = "Creates the user successfully", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))))
//        })
    @Override
    public ResponseEntity<List<User>> create(@Validated @RequestBody List<User> users) {
        users = userService.create(users);
        return ResponseEntity.ok(users);
    }

    /**
     * Updates the <code>T</code> type object.
     *
     * @param user
     * @return
     */
    @PutMapping
    @Override
    public ResponseEntity<User> update(@Validated @RequestBody User user) {
        user = userService.update(user);
        return ResponseEntity.ok(user);
    }

    /**
     * Updates the list of <code>T</code> type objects.
     *
     * @param users
     * @return
     */
    @PutMapping("/batch")
    @Override
    public ResponseEntity<List<User>> update(@Validated @RequestBody List<User> users) {
        users = userService.update(users);
        return ResponseEntity.ok(users);
    }

    /**
     * Deletes the <code>T</code> type object by <code>id</code>.
     *
     * @param idOptional
     * @return
     */
    @DeleteMapping("/{userId}")
    @Override
    public ResponseEntity<Payload> delete(@PathVariable(value = "userId") Optional<Long> idOptional) {
        validate(idOptional);
        userService.delete(idOptional.get());
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
