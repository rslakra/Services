package com.rslakra.springservices.thymeleafsidebar.tutorial.controller;

import com.rslakra.springservices.thymeleafsidebar.tutorial.persistence.entity.Tutorial;
import com.rslakra.springservices.thymeleafsidebar.tutorial.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/tutorials")
public class TutorialController {

    private final TutorialService tutorialService;

    @Autowired
    public TutorialController(final TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }

    /**
     * @param model
     * @param keyword
     * @return
     */
    @GetMapping
    public String getAll(Model model, @Param("keyword") String keyword) {
        try {
            List<Tutorial> tutorials = new ArrayList<>();
            if (keyword == null) {
                tutorialService.getAll().forEach(tutorials::add);
            } else {
                tutorialService.findByTitleContainingIgnoreCase(keyword).forEach(tutorials::add);
                model.addAttribute("keyword", keyword);
            }

            model.addAttribute("tutorials", tutorials);
        } catch (Exception e) {
            model.addAttribute("message", e.getMessage());
        }

        return "tutorial/listTutorials";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/add")
    public String addTutorial(Model model) {
        Tutorial tutorial = new Tutorial();
        tutorial.setPublished(true);
        model.addAttribute("tutorial", tutorial);
        model.addAttribute("pageTitle", "Create Tutorial");

        return "tutorial/editTutorial";
    }

    /**
     * @param tutorial
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/save")
    public String saveTutorial(Tutorial tutorial, RedirectAttributes redirectAttributes) {
        try {
            tutorialService.create(tutorial);
            redirectAttributes.addFlashAttribute("message", "The tutorial has been saved successfully!");
        } catch (Exception e) {
            redirectAttributes.addAttribute("message", e.getMessage());
        }

        return "redirect:/tutorials";
    }

    /**
     * @param id
     * @param model
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/{id}")
    public String editTutorial(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            Tutorial tutorial = tutorialService.findById(id);
            model.addAttribute("tutorial", tutorial);
            model.addAttribute("pageTitle", "Edit Tutorial (ID: " + id + ")");

            return "editTutorial";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
            return "redirect:/tutorials";
        }
    }

    /**
     * @param id
     * @param model
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/delete/{id}")
    public String deleteTutorial(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        try {
            tutorialService.delete(id);
            redirectAttributes.addFlashAttribute("message",
                                                 "The tutorial with id=" + id + " has been deleted successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/tutorials";
    }

    /**
     * @param id
     * @param published
     * @param model
     * @param redirectAttributes
     * @return
     */
    @GetMapping("/{id}/published/{status}")
    public String updatePublishedStatus(@PathVariable("id") Long id,
                                        @PathVariable("status") boolean published,
                                        Model model, RedirectAttributes redirectAttributes) {
        try {
            tutorialService.updatePublishedStatus(id, published);
            String status = published ? "published" : "disabled";
            String message = "The Tutorial id=" + id + " has been " + status;
            redirectAttributes.addFlashAttribute("message", message);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }

        return "redirect:/tutorials";
    }
}
