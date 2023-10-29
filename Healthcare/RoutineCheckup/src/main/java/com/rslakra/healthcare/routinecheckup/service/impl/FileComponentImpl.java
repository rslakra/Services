package com.rslakra.healthcare.routinecheckup.service.impl;

import com.rslakra.healthcare.routinecheckup.entity.UserEntity;
import com.rslakra.healthcare.routinecheckup.entity.UserFileEntity;
import com.rslakra.healthcare.routinecheckup.repository.UserFileRepository;
import com.rslakra.healthcare.routinecheckup.service.FileComponent;
import com.rslakra.healthcare.routinecheckup.service.UserService;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.FileStorageConstants;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.Messages;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.IncorrectFIleException;
import lombok.RequiredArgsConstructor;
import org.owasp.encoder.Encode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:18 PM
 */
@Component
@RequiredArgsConstructor
public class FileComponentImpl implements FileComponent {

    private final UserFileRepository userFileRepository;

    private final UserService userService;

    private final Messages messages;

    private final FileStorageConstants fileStorageConstants;

    private final Logger logger
        = LoggerFactory.getLogger(FileComponentImpl.class);

    @Override
    @Transactional
    public UserFileEntity saveFile(
        MultipartFile file,
        String login
    ) throws IOException {
        UserFileEntity userFile = saveUserFile(file, login);

        String fullFileName = getFullFileName(userFile);
        File fileToSave = new File(fullFileName);
        file.transferTo(fileToSave);

        return userFile;
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserFileEntity> getAllUserFiles(String login) {
        UserEntity user = userService.getUserEntityByLogin(login);
        List<UserFileEntity> userFiles
            = userFileRepository.getAllByOwner(user);

        return userFiles;
    }

    @Override
    @Transactional
    public UserFileEntity rename(
        String oldName,
        String newName,
        String login
    ) {
        UserFileEntity userFile = getUserFile(oldName, login);
        UserEntity user = userService.getUserEntityByLogin(login);
        Optional<UserFileEntity> fileWithNewName
            = userFileRepository.findByOwnerAndOriginalFileName(
            user,
            newName
        );
        if (fileWithNewName.isPresent()) {
            throw new IncorrectFIleException(
                messages.getFileAlreadyExist()
            );
        }

        String sanitizedName = Encode.forHtml(newName);
        String extension = getExtension(sanitizedName);
        String oldExtension = getExtension(oldName);
        if (!oldExtension.equals(extension)) {
            throw new IncorrectFIleException(
                messages.getIncorrectFileType()
            );
        }

        userFile.setOriginalFileName(sanitizedName);
        UserFileEntity saved = userFileRepository.save(userFile);
        return saved;
    }

    @Override
    @Transactional
    public void delete(String filename, String login) {
        UserEntity user = userService.getUserEntityByLogin(login);
        Optional<UserFileEntity> userFileNameOpt
            = userFileRepository.findByOwnerAndOriginalFileName(
            user,
            filename
        );
        if (!userFileNameOpt.isPresent()) {
            return;
        }

        UserFileEntity userFile = userFileNameOpt.get();
        String fullFileName = getFullFileName(userFile);
        userFileRepository.delete(userFile);

        Path path = Paths.get(fullFileName);
        try {
            Files.delete(path);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    @Override
    public String getExtension(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String result = getExtension(fileName);
        return result;
    }

    @Override
    public String getExtension(String fileName) {
        String extension
            = fileName.substring(fileName.lastIndexOf(".") + 1);
        return extension;
    }

    @Override
    @Transactional(readOnly = true)
    public UserFileEntity getUserFile(String fileName, String login) {
        UserEntity user = userService.getUserEntityByLogin(login);
        Optional<UserFileEntity> userFileEntity
            = userFileRepository.findByOwnerAndOriginalFileName(
            user,
            fileName
        );

        UserFileEntity entity = userFileEntity.orElseThrow(
            () -> new IncorrectFIleException(
                messages.getFileNotFound()
            )
        );
        return entity;
    }

    private String getFullFileName(UserFileEntity userFile) {
        String originalFileName = userFile.getOriginalFileName();
        String extension = getExtension(originalFileName);
        String fullFileName =
            fileStorageConstants.getMonthlyReportsBasePath() + "/"
            + userFile.getFileId()
            + "." + extension;

        return fullFileName;
    }

    private UserFileEntity saveUserFile(MultipartFile file, String login) {
        UserEntity user = userService.getUserEntityByLogin(login);

        String originalFilename = file.getOriginalFilename();
        Optional<UserFileEntity> old
            = userFileRepository.findByOwnerAndOriginalFileName(
            user,
            originalFilename
        );
        if (old.isPresent()) {
            throw new IncorrectFIleException(
                messages.getFileAlreadyExist()
            );
        }

        UserFileEntity entity = new UserFileEntity();
        String sanitizedName = Encode.forHtml(originalFilename);
        entity.setOriginalFileName(sanitizedName);
        entity.setOwner(user);
        UserFileEntity saved = userFileRepository.save(entity);

        return saved;
    }

}

