package com.rslakra.springservices.thymeleafsidebar.report.controller;

import com.rslakra.springservices.thymeleafsidebar.framework.controller.AbstractController;
import com.rslakra.springservices.thymeleafsidebar.report.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/reports")
public class ReportController extends AbstractController {

    private final ReportService reportService;

    /**
     * @param reportService
     */
    @Autowired
    public ReportController(final ReportService reportService) {
        this.reportService = reportService;
    }

    /**
     * @param principal
     * @return
     */
    @GetMapping
    public String reportIndex(Principal principal) {
//        return (Objects.nonNull(principal) ? "home/signedIn" : "home/notSignedIn");
        return "report/index";
    }

    /**
     * @return
     */
    @GetMapping({"/about-us"})
    public String aboutUs() {
        return "home/about-us";
    }

    /**
     * @return
     */
    @GetMapping({"/contact-us"})
    public String contactUs() {
        return "home/contact-us";
    }

    /**
     * @return
     */
    @GetMapping({"/sign-in"})
    public String signedIn() {
        return "home/signedIn";
    }

    /**
     * @return
     */
    @GetMapping({"/profile"})
    public String profile() {
        return "index";
    }

    /**
     * @return
     */
    @GetMapping({"/dashboard"})
    public String dashboard() {
        return "dashboard";
    }


    /**
     * @return
     */
    @GetMapping({"/settings"})
    public String settings() {
        return "settings";
    }


    /**
     * @return
     */
    @GetMapping({"/logout"})
    public String logout() {
        return "index";
    }
}
