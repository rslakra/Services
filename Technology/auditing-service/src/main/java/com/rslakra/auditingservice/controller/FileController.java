package com.rslakra.auditingservice.controller;

import com.rslakra.auditingservice.persistence.entity.File;
import com.rslakra.auditingservice.persistence.repository.FileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    private FileRepository fileRepository;

    public FileController() {
    }

    /**
     * @return
     */
    @GetMapping("/files")
    public List<File> getAllFiles() {
        return fileRepository.findAll();
    }

    /**
     * @param fileId
     * @return
     * @throws ResourceNotFoundException
     */
    @GetMapping("/file/{fileId}")
    public ResponseEntity<File> getFileById(@PathVariable(value = "fileId") Long fileId) throws ResourceNotFoundException {
        File file = fileRepository.findById(fileId).orElseThrow(() -> new ResourceNotFoundException("File not found :: " + fileId));
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
     * @throws ResourceNotFoundException
     */
    @PutMapping("/file")
    public ResponseEntity<File> updateFile(@Validated @RequestBody File file) throws ResourceNotFoundException {
        File oldFile = fileRepository.findById(file.getId())
                .orElseThrow(() -> new ResourceNotFoundException("File not found :: " + file.getId()));

        oldFile.setName(file.getName());
        oldFile.setContents(file.getContents());
        oldFile = fileRepository.save(oldFile);
        return ResponseEntity.ok(oldFile);
    }

    /**
     * @param fileId
     * @return
     * @throws ResourceNotFoundException
     */
    @DeleteMapping("/file/{fileId}")
    public Map<String, Boolean> deleteFile(@PathVariable(value = "fileId") Long fileId) throws ResourceNotFoundException {
        File file = fileRepository.findById(fileId)
                .orElseThrow(() -> new ResourceNotFoundException("File not found :: " + fileId));
        fileRepository.delete(file);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
    }
}
