package com.rslakra.healthcare.routinecheckup.service;

import com.rslakra.healthcare.routinecheckup.dto.request.UpdateMonthlyReportRequestDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:15 PM
 */
public interface MonthlyReportsService {

    String saveReport(MultipartFile file, String login);

    void deleteReport(String fileName, String login);

    String renameReport(UpdateMonthlyReportRequestDto dto, String login);

    List<String> getAllReportsNames(String login);

    String getReportName(String reportName, String login);

}
