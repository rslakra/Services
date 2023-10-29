package com.rslakra.springservices.thymeleafsidebarlayouts.home.controller;

import com.rslakra.springservices.thymeleafsidebarlayouts.home.persistence.entity.Lead;
import com.rslakra.springservices.thymeleafsidebarlayouts.home.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Rohtash Lakra
 * @created 2/9/23 3:49 PM
 */
@Controller
@RequestMapping("/leads")
public class LeadWebController {

    private final LeadService leadService;

    /**
     * @param leadService
     */
    @Autowired
    public LeadWebController(final LeadService leadService) {
        this.leadService = leadService;
    }

    /**
     * @param model
     * @param lead
     * @return
     */
    @GetMapping({"/contact-us"})
    public String contactUs(Model model, Lead lead) {
        if (lead == null) {
            lead = new Lead();
        }

        model.addAttribute("lead", lead);
        return "views/contact-us";
    }

    /**
     * @param redirectAttributes
     * @param lead
     * @return
     */
    @PostMapping("/save")
    public String save(RedirectAttributes redirectAttributes, Lead lead) {
        try {
            lead = leadService.create(lead);
            redirectAttributes.addFlashAttribute("message", "The lead has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/leads/contact-us";
    }

}
