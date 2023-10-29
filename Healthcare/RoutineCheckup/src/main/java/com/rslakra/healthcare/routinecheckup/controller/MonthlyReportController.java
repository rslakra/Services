package com.rslakra.healthcare.routinecheckup.controller;

import com.rslakra.healthcare.routinecheckup.dto.request.UpdateMonthlyReportRequestDto;
import com.rslakra.healthcare.routinecheckup.dto.response.MonthlyReportResponseDto;
import com.rslakra.healthcare.routinecheckup.service.MonthlyReportsService;
import com.rslakra.healthcare.routinecheckup.utils.constants.ModelAttributesNames;
import com.rslakra.healthcare.routinecheckup.utils.constants.ViewNames;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:59 PM
 */
@Controller
@RequiredArgsConstructor
public class MonthlyReportController {

    private final MonthlyReportsService monthlyReportsService;

    @GetMapping(value = ViewNames.MONTHLY_REPORTS_URL)
    public String monthlyReportView(Model model, Principal principal) {
        String login = principal.getName();
        List<String> allReportsNames
            = monthlyReportsService.getAllReportsNames(login);

        model.addAttribute(
            ModelAttributesNames.REPORTS_NAMES,
            allReportsNames
        );
        return ViewNames.MONTHLY_REPORTS_VIEW_NAME;
    }

    @PostMapping(value = ViewNames.MONTHLY_REPORTS_URL)
    public String uploadReport(
        @RequestParam("file") MultipartFile file,
        Principal principal
    ) {
        String login = principal.getName();
        monthlyReportsService.saveReport(file, login);

        return "redirect:" + ViewNames.MONTHLY_REPORTS_URL;
    }

    @GetMapping(
        value = ViewNames.RENAME_MONTHLY_REPORT_URL + "/{file_name}"
    )
    public String updateReportView(
        @PathVariable("file_name") String fileName,
        Model model,
        Principal principal
    ) {
        String login = principal.getName();
        String reportName
            = monthlyReportsService.getReportName(fileName, login);

        UpdateMonthlyReportRequestDto dto
            = new UpdateMonthlyReportRequestDto();
        dto.setOldName(reportName);
        model.addAttribute(ModelAttributesNames.RENAME_REPORT_DTO, dto);

        return ViewNames.RENAME_MONTHLY_REPORT_VIEW_NAME;
    }

    @PostMapping(value = ViewNames.RENAME_MONTHLY_REPORT_URL)
    public String updateReport(
        @ModelAttribute(ModelAttributesNames.RENAME_REPORT_DTO)
        @Valid
            UpdateMonthlyReportRequestDto dto,
        Principal principal
    ) {
        String login = principal.getName();
        monthlyReportsService.renameReport(dto, login);

        return "redirect:" + ViewNames.MONTHLY_REPORTS_URL;
    }

    @DeleteMapping(value = ViewNames.MONTHLY_REPORTS_URL + "/{file_name}")
    @ResponseBody
    public MonthlyReportResponseDto deleteReport(
        @PathVariable("file_name") String fileName,
        Principal principal
    ) {
        String login = principal.getName();
        monthlyReportsService.deleteReport(fileName, login);

        MonthlyReportResponseDto dto = new MonthlyReportResponseDto();
        dto.setFileName(fileName);
        return dto;
    }

}
