package com.rslakra.melody.ews.account.controller.web;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.controller.web.AbstractWebController;
import com.devamatre.framework.spring.controller.web.WebController;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.parser.Parser;
import com.devamatre.framework.spring.parser.csv.CsvParser;
import com.devamatre.framework.spring.parser.excel.ExcelParser;
import com.rslakra.melody.ews.account.parser.RoleParser;
import com.rslakra.melody.ews.account.payload.dto.Role;
import com.rslakra.melody.ews.account.service.RoleService;
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
import java.util.Map;
import java.util.Objects;

/**
 * @author: Rohtash Lakra (rlakra)
 * @since: 9/30/19 5:38 PM
 */
@Controller
@RequestMapping("/roles")
public class RoleWebController extends AbstractWebController<Role, Long> implements WebController<Role, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleWebController.class);

    private final RoleParser roleParser;
    // roleService
    private final RoleService roleService;

    /**
     * @param roleService
     */
    @Autowired
    public RoleWebController(RoleService roleService) {
        this.roleParser = new RoleParser();
        this.roleService = roleService;
    }

    /**
     * Saves the <code>t</code> object.
     *
     * @param role
     * @return
     */
    @PostMapping("/save")
    @Override
    public String save(Role role) {
        if (BeanUtils.isNotNull(role.getId())) {
            Role oldRole = roleService.getById(role.getId());
            BeanUtils.copyProperties(role, oldRole);
            role = roleService.update(oldRole);
        } else {
            role = roleService.create(role);
        }

        return "redirect:/roles/list";
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
        List<Role> roles = roleService.getAll();
        model.addAttribute("roles", roles);

        return "views/account/role/listRoles";
    }

    /**
     * Filters the list of <code>T</code> objects.
     *
     * @param model
     * @param filter
     * @return
     */
    @Override
    public String filter(Model model, Filter filter) {
        return null;
    }

    /**
     * @param model
     * @param allParams
     * @return
     */
    @Override
    public String filter(Model model, Map<String, Object> allParams) {
        return null;
    }

    /**
     * Create the new object or Updates the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping(path = {"/create", "/update/{id}"})
    @Override
    public String editObject(Model model, @PathVariable(name = "id") Long id) {
        Role role = null;
        if (BeanUtils.isNotNull(id)) {
            role = roleService.getById(id);
        } else {
            role = new Role();
        }
        model.addAttribute("role", role);

        return "views/account/role/editRole";
    }

    /**
     * Deletes the object with <code>id</code>.
     *
     * @param model
     * @param id
     * @return
     */
    @RequestMapping("/delete/{id}")
    @Override
    public String delete(Model model, @PathVariable(name = "id") Long id) {
        roleService.delete(id);
        return "redirect:/roles/list";
    }

    /**
     * @return
     */
    @Override
    public Parser<Role> getParser() {
        return roleParser;
    }

    /**
     * Displays the upload <code>Roles</code> UI.
     *
     * @return
     */
    @GetMapping(path = {"/upload"})
    public String showUploadPage() {
        return "views/account/role/uploadRoles";
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
            List<Role> roles = null;
            RoleParser roleParser = new RoleParser();
            if (CsvParser.isCSVFile(file)) {
                roles = roleParser.readCSVStream(file.getInputStream());
            } else if (ExcelParser.isExcelFile(file)) {
                roles = roleParser.readStream(file.getInputStream());
            }

            // check the task list is available
            if (Objects.nonNull(roles)) {
                roles = roleService.create(roles);
                LOGGER.debug("roles: {}", roles);
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
    public ResponseEntity<Resource> download(@RequestParam("fileType") String fileType) {
        BeanUtils.assertNonNull(fileType, "Download 'fileType' must provide!");
        ResponseEntity responseEntity = null;
        InputStreamResource inputStreamResource = null;
        String contentDisposition;
        MediaType mediaType;
        RoleParser taskParser = new RoleParser();
        if (CsvParser.isCSVFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(RoleParser.CSV_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(CsvParser.CSV_MEDIA_TYPE);
            inputStreamResource = taskParser.buildCSVResourceStream(roleService.getAll());
        } else if (ExcelParser.isExcelFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(RoleParser.EXCEL_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(ExcelParser.EXCEL_MEDIA_TYPE);
            inputStreamResource = taskParser.buildStreamResources(roleService.getAll());
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
