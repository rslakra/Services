package com.rslakra.libraryservice.controller.rest;

import com.rslakra.libraryservice.persistence.entity.User;
import com.rslakra.libraryservice.payload.PayloadBuilder;
import com.rslakra.libraryservice.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/5/21 11:11 AM
 */
@RestController
@RequestMapping("${apiPrefix}/users")
@Tag(name = "User Service")
public class UserController {

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
     * @return
     */
    @GetMapping
    @ResponseBody
    @Operation(summary = "Get all users", description = "Get all users",
        tags = {"User Service"},
        responses = {
            @ApiResponse(responseCode = "200", description = "Get the users successfully", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))))
        })
    public List<User> getAllUsers() {
        return userService.getAll();
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    @Operation(summary = "Get user by id", description = "Get user by id",
        tags = {"User Service"},
        responses = {
            @ApiResponse(responseCode = "200", description = "Gets the users successfully", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "404", description = "No record found")
        })
    public ResponseEntity<User> getUserById(
        @Parameter(required = true, description = "User ID") @PathVariable(value = "id") Long userId) {
        return ResponseEntity.ok().body(userService.getById(userId));
    }

    /**
     * Returns the list of users by userName.
     *
     * @param userName
     * @return
     */
    @GetMapping("/byUserName/{userName}")
    @ResponseBody
    public User getByUserName(@PathVariable(value = "userName") String userName) {
        return userService.getByUserName(userName);
    }

    /**
     * Returns the list of users by firstName.
     *
     * @param firstName
     * @return
     */
    @GetMapping("/byFirstName/{firstName}")
    @ResponseBody
    public List<User> getByFirstName(@PathVariable(value = "firstName") String firstName) {
        return userService.getByFirstName(firstName);
    }

    /**
     * Returns the list of users by lastName.
     *
     * @param lastName
     * @return
     */
    @GetMapping("/byLastName/{lastName}")
    @ResponseBody
    public List<User> getByLastName(@PathVariable(value = "lastName") String lastName) {
        return userService.getByLastName(lastName);
    }

    /**
     * Returns the list of users by email.
     *
     * @param email
     * @return
     */
    @GetMapping("/byEmail/{email}")
    @ResponseBody
//    public List<User> getByEmail(@PathVariable(value = "email") String email) {
//        return userService.getByEmail(email);
//    }
    public User getByEmail(@PathVariable(value = "email") String email) {
        return userService.getByEmail(email);
    }

    /**
     * @param pageable
     * @return
     */
    @GetMapping("/filter")
    public Page<User> getByFilter(Pageable pageable) {
        return userService.getByFilter(pageable);
    }

    /**
     * @param user
     * @return
     */
    @PostMapping
    @ResponseBody
    @Operation(summary = "Create new user", description = "Create new user",
        tags = {"User Service"},
        responses = {
            @ApiResponse(responseCode = "200", description = "Creates the user successfully", content = @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = User.class))))
        })
    public User createUser(
        @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Create new user", content = @Content(mediaType = "application/json", schema = @Schema(implementation = User.class)), required = true)
        @Validated @RequestBody User user) {
        return userService.upsert(user);
    }

    /**
     * @param user
     * @return
     */
    @PutMapping
    @ResponseBody
    public ResponseEntity<User> updateUser(@Validated @RequestBody User user) {
        return ResponseEntity.ok(userService.upsert(user));
    }

    /**
     * @param users
     * @return
     */
    @PostMapping("/batch")
    @ResponseBody
    public List<User> upsert(@RequestBody List<User> users) {
        return userService.upsert(users);
    }

    /**
     * @param userId
     * @return
     */
    @DeleteMapping("/{userId}")
    @ResponseBody
    public ResponseEntity<PayloadBuilder> delete(@PathVariable(value = "userId") Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok(PayloadBuilder.builder()
                                     .withDeleted( Boolean.TRUE)
                                     .withMessage("Record with id:" + userId + " deleted successfully!")
        );
    }

}
