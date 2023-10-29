package com.rslakra.healthcare.routinecheckup.service;

import com.rslakra.healthcare.routinecheckup.entity.UserFileEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:14 PM
 */
public interface FileComponent {

    UserFileEntity saveFile(MultipartFile file, String login)
        throws IOException;

    List<UserFileEntity> getAllUserFiles(String login);

    UserFileEntity rename(String oldName, String newName, String login);

    void delete(String filename, String login);

    String getExtension(MultipartFile file);

    String getExtension(String fileName);

    UserFileEntity getUserFile(String fileName, String login);

}
