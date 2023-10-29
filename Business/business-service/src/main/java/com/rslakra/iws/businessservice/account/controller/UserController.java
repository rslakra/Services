package com.rslakra.iws.businessservice.account.controller;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.frameworks.core.Payload;
import com.rslakra.frameworks.spring.controller.rest.AbstractRestController;
import com.rslakra.frameworks.spring.parser.Parser;
import com.rslakra.frameworks.spring.parser.csv.CsvParser;
import com.rslakra.frameworks.spring.parser.excel.ExcelParser;
import com.rslakra.iws.businessservice.account.filter.UserFilter;
import com.rslakra.iws.businessservice.account.parser.UserParser;
import com.rslakra.iws.businessservice.account.persistence.entity.User;
import com.rslakra.iws.businessservice.account.service.UserService;
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
 * @author Rohtash Lakra
 * @created 8/5/21 11:11 AM
 */
@RestController
@RequestMapping("${restPrefix}/users")
//@Tag(name = "User Service")
public class UserController extends AbstractRestController<User> {

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
    public List<User> getByFilter(@RequestParam Map<String, String> allParams) {
        LOGGER.debug("+getByFilter({})", allParams);
        List<User> users = Collections.emptyList();
        UserFilter userFilter = new UserFilter(allParams);
        if (userFilter.hasKeys(UserFilter.EMAIL, UserFilter.FIRST_NAME, UserFilter.LAST_NAME)) {
        } else if (userFilter.hasKeys(UserFilter.FIRST_NAME, UserFilter.LAST_NAME)) {
        } else if (userFilter.hasKey(UserFilter.ID)) {
            users = Arrays.asList(userService.getById(userFilter.getLong(UserFilter.ID)));
        } else if (userFilter.hasKey(UserFilter.EMAIL)) {
            users = Arrays.asList(userService.getByEmail(userFilter.getValue(UserFilter.EMAIL)));
        } else if (userFilter.hasKey(UserFilter.FIRST_NAME)) {
            users = userService.getByFirstName(userFilter.getValue(UserFilter.FIRST_NAME));
        } else if (userFilter.hasKey(UserFilter.LAST_NAME)) {
            users = userService.getByLastName(userFilter.getValue(UserFilter.LAST_NAME));
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
    public Page<User> getByFilter(Map<String, String> allParams, Pageable pageable) {
        return userService.getByFilter(null, pageable);
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
            List<User> userList = null;
            UserParser userParser = new UserParser();
            if (CsvParser.isCSVFile(file)) {
                userList = userParser.readCSVStream(file.getInputStream());
            } else if (ExcelParser.isExcelFile(file)) {
                userList = userParser.readStream(file.getInputStream());
            }

            // check the user list is available
            if (Objects.nonNull(userList)) {
                userList = userService.create(userList);
                LOGGER.debug("userList: {}", userList);
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
        UserParser userParser = new UserParser();
        if (CsvParser.isCSVFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(UserParser.CSV_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(CsvParser.CSV_MEDIA_TYPE);
            inputStreamResource = userParser.buildCSVResourceStream(userService.getAll());
        } else if (ExcelParser.isExcelFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(UserParser.EXCEL_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(ExcelParser.EXCEL_MEDIA_TYPE);
            inputStreamResource = userParser.buildStreamResources(userService.getAll());
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
