package com.rslakra.libraryservice.controller.rest;

import com.rslakra.libraryservice.persistence.entity.File;
import com.rslakra.libraryservice.payload.PayloadBuilder;
import com.rslakra.libraryservice.service.FileService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 6:47 PM
 */
@RestController
@RequestMapping("${apiPrefix}/files")
@Tag(name = "File Service")
public class FileController {

    private final FileService fileService;

    /**
     * @param fileService
     */
    @Autowired
    public FileController(final FileService fileService) {
        this.fileService = fileService;
    }

    /**
     * @return
     */
    @GetMapping
    public List<File> getAllFiles() {
        return fileService.getAll();
    }

    /**
     * @param fileId
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<File> getFileById(@PathVariable(value = "id") Long fileId) {
        File file = fileService.getById(fileId);
        return ResponseEntity.ok().body(file);
    }

    /**
     * @param file
     * @return
     */
    @PostMapping
    public File addFile(@Validated @RequestBody File file) {
        return fileService.upsert(file);
    }

    /**
     * @param files
     * @return
     */
    @PostMapping("/batch")
    public List<File> addFiles(@Validated @RequestBody List<File> files) {
        return fileService.upsert(files);
    }

    /**
     * @param file
     * @return
     */
    @PutMapping
    public ResponseEntity<File> updateFile(@Validated @RequestBody File file) {
        file = fileService.upsert(file);
        return ResponseEntity.ok(file);
    }

    /**
     * @param fileId
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<PayloadBuilder> deleteFile(@PathVariable(value = "id") Long fileId) {
        fileService.delete(fileId);
        return ResponseEntity.ok(PayloadBuilder.builder()
                                     .withDeleted(Boolean.TRUE)
                                     .withMessage("Record with id:" + fileId + " deleted successfully!")
        );
    }
}
