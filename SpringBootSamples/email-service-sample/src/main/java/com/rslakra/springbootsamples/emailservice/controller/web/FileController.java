package com.rslakra.springbootsamples.emailservice.controller.web;

import com.rslakra.springbootsamples.emailservice.service.AnalysisService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:15 PM
 */
@Controller
@RequiredArgsConstructor
public class FileController {

    private final AnalysisService analysisService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/file")
    public String getFileForm(Model model) {
        model.addAttribute("fileNames", analysisService.getAllSerializedFiles());
        return "file";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/file")
    public void downloadFile(@ModelAttribute("filename") @Validated String fileName, HttpServletResponse servletResponse) {
        analysisService.getSerializedFile(fileName, servletResponse);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value = "/delete-file")
    public String getDeleteFileForm(Model model) {
        model.addAttribute("fileNames", analysisService.getAllSerializedFiles());
        return "delete-file";
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(value = "/delete-file")
    public ModelAndView deleteFile(@ModelAttribute("filename") @Validated String fileName) {
        if (analysisService.deleteSerializedFile(fileName)) {
            return new ModelAndView("redirect:/delete-file");
        }

        return new ModelAndView("delete-file", "filename", fileName);
    }
}
