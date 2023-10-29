package com.rslakra.iws.businessservice.account.controller;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.controller.rest.AbstractRestController;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.parser.Parser;
import com.devamatre.framework.spring.parser.csv.CsvParser;
import com.devamatre.framework.spring.parser.excel.ExcelParser;
import com.rslakra.iws.businessservice.account.filter.RoleFilter;
import com.rslakra.iws.businessservice.account.parser.RoleParser;
import com.rslakra.iws.businessservice.account.persistence.entity.Role;
import com.rslakra.iws.businessservice.account.service.RoleService;
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
 * @created 2/8/23 1:57 PM
 */
@RestController
@RequestMapping("${restPrefix}/roles")
public class RoleController extends AbstractRestController<Role, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleController.class);
    private final RoleService roleService;

    /**
     * @param roleService
     */
    @Autowired
    public RoleController(final RoleService roleService) {
        this.roleService = roleService;
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @return
     */
    @GetMapping
    @Override
    public List<Role> getAll() {
        return roleService.getAll();
    }

    /**
     * Returns the list of <code>T</code> filters objects.
     *
     * @param allParams
     * @return
     */
    @GetMapping("/filter")
    @Override
    public List<Role> getByFilter(@RequestParam Map<String, Object> allParams) {
        LOGGER.debug("+getByFilter({})", allParams);
        List<Role> roles = Collections.emptyList();
        RoleFilter roleFilter = new RoleFilter(allParams);
        if (roleFilter.hasKeys(RoleFilter.ID, RoleFilter.NAME)) {
        } else if (roleFilter.hasKey(RoleFilter.ID)) {
            roles = Arrays.asList(roleService.getById(roleFilter.getLong(RoleFilter.ID)));
        } else if (roleFilter.hasKey(RoleFilter.NAME)) {
            roles = Arrays.asList(roleService.getByName(roleFilter.getValue(RoleFilter.NAME, String.class)));
        } else {
            roles = roleService.getAll();
        }

        LOGGER.debug("-getByFilter(), roles: {}", roles);
        return roles;
    }

    /**
     * Returns the <code>Page<T></code> list of objects filtered with <code>allParams</code>.
     *
     * @param allParams @return
     * @param pageable
     */
    @GetMapping("/pageable")
    @Override
    public Page<Role> getByFilter(Map<String, Object> allParams, Pageable pageable) {
        return roleService.getByFilter(null, pageable);
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<Role> getByFilter(Filter filter) {
        return null;
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Role> getByFilter(Filter filter, Pageable pageable) {
        return null;
    }

    /**
     * Creates the <code>T</code> type object.
     *
     * @param role
     * @return
     */
    @PostMapping
    @ResponseBody
    @Override
    public ResponseEntity<Role> create(@Validated @RequestBody Role role) {
        role = roleService.create(role);
        return ResponseEntity.ok(role);
    }

    /**
     * Creates the list of <code>T</code> type objects.
     *
     * @param roles
     * @return
     */
    @PostMapping("/batch")
    @ResponseBody
    @Override
    public ResponseEntity<List<Role>> create(@Validated @RequestBody List<Role> roles) {
        roles = roleService.create(roles);
        return ResponseEntity.ok(roles);
    }

    /**
     * Updates the <code>T</code> type object.
     *
     * @param role
     * @return
     */
    @PutMapping
    @Override
    public ResponseEntity<Role> update(@Validated @RequestBody Role role) {
        role = roleService.update(role);
        return ResponseEntity.ok(role);
    }

    /**
     * Updates the list of <code>T</code> type objects.
     *
     * @param roles
     * @return
     */
    @PutMapping("/batch")
    @Override
    public ResponseEntity<List<Role>> update(@Validated @RequestBody List<Role> roles) {
        roles = roleService.update(roles);
        return ResponseEntity.ok(roles);
    }

    /**
     * Deletes the <code>T</code> type object by <code>id</code>.
     *
     * @param idOptional
     * @return
     */
    @DeleteMapping("/{roleId}")
    @Override
    public ResponseEntity<Payload> delete(@PathVariable(value = "roleId") Optional<Long> idOptional) {
        validate(idOptional);
        roleService.delete(idOptional.get());
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
            List<Role> roles = null;
            RoleParser roleParser = new RoleParser();
            if (CsvParser.isCSVFile(file)) {
                roles = roleParser.readCSVStream(file.getInputStream());
            } else if (ExcelParser.isExcelFile(file)) {
                roles = roleParser.readStream(file.getInputStream());
            }

            // check the user list is available
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
    @Override
    public ResponseEntity<Resource> download(@RequestParam("fileType") String fileType) {
        BeanUtils.assertNonNull(fileType, "Download 'fileType' must provide!");
        ResponseEntity responseEntity = null;
        InputStreamResource inputStreamResource = null;
        String contentDisposition;
        MediaType mediaType;
        RoleParser roleParser = new RoleParser();
        if (CsvParser.isCSVFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(RoleParser.CSV_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(CsvParser.CSV_MEDIA_TYPE);
            inputStreamResource = roleParser.buildCSVResourceStream(roleService.getAll());
        } else if (ExcelParser.isExcelFileType(fileType)) {
            contentDisposition = Parser.getContentDisposition(RoleParser.EXCEL_DOWNLOAD_FILE_NAME);
            mediaType = Parser.getMediaType(ExcelParser.EXCEL_MEDIA_TYPE);
            inputStreamResource = roleParser.buildStreamResources(roleService.getAll());
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
