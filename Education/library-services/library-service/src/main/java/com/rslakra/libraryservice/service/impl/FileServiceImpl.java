package com.rslakra.libraryservice.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.exception.DuplicateRecordException;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.rslakra.libraryservice.persistence.entity.File;
import com.rslakra.libraryservice.persistence.entity.FileHistory;
import com.rslakra.libraryservice.persistence.repository.FileHistoryRepository;
import com.rslakra.libraryservice.persistence.repository.FileRepository;
import com.rslakra.libraryservice.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 5:50 PM
 */
@Service
public class FileServiceImpl extends BaseServiceImpl<File> implements FileService {

    // LOGGER
    private static final Logger LOGGER = LoggerFactory.getLogger(FileServiceImpl.class);

    // fileRepository
    private final FileRepository fileRepository;
    private final FileHistoryRepository fileHistoryRepository;

    /**
     * @param fileRepository
     */
    @Autowired
    public FileServiceImpl(final FileRepository fileRepository, final FileHistoryRepository fileHistoryRepository) {
        this.fileRepository = fileRepository;
        this.fileHistoryRepository = fileHistoryRepository;
    }

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    @Override
    public List<File> getAll() {
        final List<File> files = fileRepository.findAll();
        LOGGER.debug("getAllObjects(), files:{}", files);
        return files;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public File getById(Long id) {
        LOGGER.debug("getById({})", id);
        return fileRepository.findById(id)
            .orElseThrow(() -> new NoRecordFoundException("id:%d", id));

    }

    /**
     * @param file
     * @return
     */
    @Override
    public File upsert(File file) {
        LOGGER.debug("+upsert({})", file);
        Objects.requireNonNull(file);
        File oldFile = file;
        if (BeanUtils.isNull(file.getId())) {
            if (BeanUtils.isNull(file.getName())) {
                throw new InvalidRequestException();
            } else if (fileRepository.getByName(file.getName()).isPresent()) {
                throw new DuplicateRecordException("name:%s", file.getName());
            }

            LOGGER.info("Creating {}", file);
        } else { // update file
            LOGGER.info("Updating {}", file);
            oldFile =
                fileRepository.findById(file.getId())
                    .orElseThrow(() -> new NoRecordFoundException("fileId:%d", file.getId()));

            // update object
            BeanUtils.copyProperties(file, oldFile, IGNORED_PROPERTIES);
        }

        // persist user
        oldFile = fileRepository.saveAndFlush(oldFile);
        LOGGER.debug("-upsert(), oldFile:{}", oldFile);
        return oldFile;
    }

    /**
     * @param objectList
     * @return
     */
    @Override
    public List<File> upsert(List objectList) {
        final List<File> updatedFiles = new ArrayList<>();
        objectList.forEach(file -> updatedFiles.add(upsert((File) file)));
//        return fileRepository.saveAllAndFlush(files);
        return updatedFiles;
    }

    /**
     * @param name
     * @return
     */
    @Override
    public File getByName(String name) {
        Optional<File> file = fileRepository.getByName(name);
        if (!file.isPresent()) {
            throw new NoRecordFoundException("name:" + name);
        }

        return file.get();
    }

    /**
     * @param contents
     * @return
     */
    @Override
    public List<File> getByContents(String contents) {
        return fileRepository.getByContents(contents);
    }

    /**
     * @param pageable
     * @return
     */
    @Override
    public Page<File> getByFilter(Pageable pageable) {
        return fileRepository.findAll(pageable);
    }

    /**
     * @param id
     */
    @Override
    public void delete(final Long id) {
        LOGGER.debug("delete({})", id);
        Objects.requireNonNull(id);
        File file = fileRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%d", id));
        List<FileHistory> fileHistories = fileHistoryRepository.getAllByFileId(file.getId());
        LOGGER.info("Deleting {}", fileHistories);
        fileHistoryRepository.deleteAll(fileHistories);
        LOGGER.info("Deleting {}", file);
        fileRepository.deleteById(id);
    }
}
