package com.rslakra.healthcare.routinecheckup.service.impl;

import com.rslakra.healthcare.routinecheckup.dto.request.UpdateMonthlyReportRequestDto;
import com.rslakra.healthcare.routinecheckup.entity.UserFileEntity;
import com.rslakra.healthcare.routinecheckup.service.FileComponent;
import com.rslakra.healthcare.routinecheckup.service.MonthlyReportsService;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.Messages;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.IncorrectFIleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:18 PM
 */
@Service
@RequiredArgsConstructor
public class MonthlyReportsServiceImpl implements MonthlyReportsService {

    private final Messages messages;

    private final FileComponent fileComponent;

    private final String[] FILE_EXTENSIONS_ALLOWLIST = {
        "doc",
        "docx"
    };

    private final String[] MIME_TYPE_ALLOWLIST = {
        "application/msword",
        "application/vnd.openxmlformats-officedocument.wordprocessingml" +
        ".document"
    };

    @Override
    public String saveReport(MultipartFile file, String login) {
        String extension = fileComponent.getExtension(file);
        validateMimeType(file.getContentType());
        validateExtension(extension);

        try {
            UserFileEntity saved = fileComponent.saveFile(file, login);
            return saved.getOriginalFileName();
        } catch (IOException e) {
            throw new RuntimeException("Cannot save file!", e);
        }
    }

    @Override
    public void deleteReport(String fileName, String login) {
        fileComponent.delete(fileName, login);
    }

    @Override
    public String renameReport(UpdateMonthlyReportRequestDto dto, String login) {
        String newName = dto.getNewName();
        String extension = fileComponent.getExtension(newName);
        validateExtension(extension);

        String oldName = dto.getOldName();
        UserFileEntity renamed
            = fileComponent.rename(oldName, newName, login);
        return renamed.getOriginalFileName();
    }

    @Override
    public List<String> getAllReportsNames(String login) {
        List<UserFileEntity> allUserFiles
            = fileComponent.getAllUserFiles(login);
        List<String> result = allUserFiles.stream()
            .map(UserFileEntity::getOriginalFileName)
            .collect(Collectors.toList());
        return result;
    }

    @Override
    public String getReportName(String reportName, String login) {
        UserFileEntity userFile
            = fileComponent.getUserFile(reportName, login);
        return userFile.getOriginalFileName();
    }

    private void validateMimeType(String mimeType) {
        boolean isCorrectMimeType = Arrays.stream(MIME_TYPE_ALLOWLIST)
            .anyMatch(t -> t.equals(mimeType));

        if (!isCorrectMimeType) {
            throw new IncorrectFIleException(
                messages.getIncorrectFileType()
            );
        }
    }

    private void validateExtension(String extension) {
        boolean isCorrectExtension = Arrays.stream(FILE_EXTENSIONS_ALLOWLIST)
            .anyMatch(e -> e.equals(extension));

        if (!isCorrectExtension) {
            throw new IncorrectFIleException(
                messages.getIncorrectFileType()
            );
        }
    }
}
