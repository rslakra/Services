package com.rslakra.auditingservice.controller;

import com.rslakra.auditingservice.persistence.entity.File;
import com.rslakra.auditingservice.persistence.repository.FileRepository;
import com.rslakra.frameworks.spring.exception.NoRecordFoundException;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 6:47 PM
 */
@RestController
@RequestMapping("/api/v1")
public class FileController {

    private final FileRepository fileRepository;

    /**
     * @param fileRepository
     */
    @Autowired
    public FileController(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    /**
     * @return
     */
    @GetMapping("/files")
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    /**
     * Returns the <code>file</code> object for the provided <code>fileId</code>.
     *
     * @param fileId
     * @return
     */
    protected File findById(Long fileId) {
        return fileRepository.findById(fileId).orElseThrow(() -> new NoRecordFoundException("fileId: %d", fileId));
    }

    /**
     * @param fileId
     * @return
     * @throws NoRecordFoundException
     */
    @GetMapping("/file/{fileId}")
    public ResponseEntity<File> getFileById(@PathVariable(value = "fileId") Long fileId)
        throws NoRecordFoundException {
        File file = findById(fileId);
        return ResponseEntity.ok().body(file);
    }

    /**
     * @param file
     * @return
     */
    @PostMapping("/file")
    public File addFile(@Validated @RequestBody File file) {
        return fileRepository.save(file);
    }

    /**
     * @param file
     * @return
     */
    @PutMapping("/file")
    public ResponseEntity<File> updateFile(@Validated @RequestBody File file) {
        File oldFile = findById(file.getId());
        oldFile.setName(file.getName());
        oldFile.setContents(file.getContents());
        oldFile = fileRepository.save(oldFile);
        return ResponseEntity.ok(oldFile);
    }

    /**
     * @param fileId
     * @return
     */
    @DeleteMapping("/file/{fileId}")
    public Map<String, Boolean> deleteFile(@PathVariable(value = "fileId") Long fileId) {
        File file = findById(fileId);
        fileRepository.delete(file);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
