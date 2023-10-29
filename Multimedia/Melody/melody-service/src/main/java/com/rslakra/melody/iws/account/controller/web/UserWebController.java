package com.rslakra.melody.iws.account.controller.web;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.frameworks.core.Payload;
import com.rslakra.frameworks.spring.controller.web.AbstractWebController;
import com.rslakra.frameworks.spring.controller.web.WebController;
import com.rslakra.frameworks.spring.exception.InvalidRequestException;
import com.rslakra.frameworks.spring.filter.Filter;
import com.rslakra.frameworks.spring.parser.Parser;
import com.rslakra.frameworks.spring.parser.csv.CsvParser;
import com.rslakra.frameworks.spring.parser.excel.ExcelParser;
import com.rslakra.melody.iws.account.parser.UserParser;
import com.rslakra.melody.iws.account.persistence.entity.User;
import com.rslakra.melody.iws.account.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: Rohtash Lakra
 * @since: 9/30/19 5:38 PM
 */
@Controller
@RequestMapping("/users")
public class UserWebController extends AbstractWebController<User> implements WebController<User> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserWebController.class);

    private final UserParser userParser;

    // userService
    private final UserService userService;

    /**
     * @param userService
     */
    @Autowired
    public UserWebController(UserService userService) {
        this.userParser = new UserParser();
        this.userService = userService;
    }

    /**
     * @param id
     */
    @Override
    public void validate(Optional<Long> id) {
    }

    /**
     * Saves the <code>t</code> object.
     *
     * @param user
     * @return
     */
    @PostMapping("/save")
    @Override
    public String save(User user) {
        if (BeanUtils.isNotNull(user.getId())) {
            User oldUser = userService.getById(user.getId());
            if (!oldUser.getEmail().equals(user.getEmail())) {
                throw new InvalidRequestException();
            }
            BeanUtils.copyProperties(user, oldUser);
            user = userService.update(oldUser);
        } else {
            user = userService.create(user);
        }

        return "redirect:/users/list";
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
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "account/user/listUsers";
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
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "account/user/listUsers";
    }

    /**
     * @param model
     * @param userId
     * @return
     */
    @RequestMapping(path = {"/create", "/update/{userId}"})
    public String editObject(Model model, @PathVariable(name = "userId") Optional<Long> userId) {
        User user = null;
        if (userId.isPresent()) {
            user = userService.getById(userId.get());
        } else {
            user = new User();
        }
        model.addAttribute("user", user);

        return "account/user/editUser";
    }

    /**
     * Deletes the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/delete/{userId}")
    @Override
    public String delete(Model model, @PathVariable(name = "userId") Optional<Long> id) {
        validate(id);
        userService.delete(id.get());
        return "redirect:/users/list";
    }

    /**
     * @return
     */
    @Override
    public Parser<User> getParser() {
        return userParser;
    }

    /**
     * Displays the upload <code>Users</code> UI.
     *
     * @return
     */
    @GetMapping(path = {"/upload"})
    public String showUploadPage() {
        return "account/user/uploadUsers";
    }

    /**
     * Uploads the file of <code>Roles</code>.
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public ResponseEntity<Payload> upload(@RequestParam("file") MultipartFile file) {
        Payload payload = Payload.newBuilder();
        try {
            List<User> users = null;
            UserParser userParser = new UserParser();
            if (CsvParser.isCSVFile(file)) {
                users = userParser.readCSVStream(file.getInputStream());
            } else if (ExcelParser.isExcelFile(file)) {
                users = userParser.readStream(file.getInputStream());
            }

            // check the task list is available
            if (Objects.nonNull(users)) {
                users = userService.create(users);
                LOGGER.debug("users: {}", users);
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
     * @return
     */
    @Override
    public String showDownloadPage() {
        return null;
    }

    /**
     * Downloads the object of <code>T</code> as <code>fileType</code> file.
     *
     * @param fileType
     * @return
     */
    @GetMapping("/download")
    public ResponseEntity<Resource> download(@RequestParam("fileType") String fileType) {
        BeanUtils.assertNonNull(fileType, "Download 'fileType' must provide!");
        ResponseEntity responseEntity = null;
        InputStreamResource inputStreamResource = null;
        String contentDisposition;
        MediaType mediaType;
        UserParser taskParser = new UserParser();
        if (CsvParser.isCSVFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(UserParser.CSV_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(CsvParser.CSV_MEDIA_TYPE);
            inputStreamResource = taskParser.buildCSVResourceStream(userService.getAll());
        } else if (ExcelParser.isExcelFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(UserParser.EXCEL_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(ExcelParser.EXCEL_MEDIA_TYPE);
            inputStreamResource = taskParser.buildStreamResources(userService.getAll());
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
