package com.rslakra.springservices.thymeleafsidebarlayouts.tutorial.controller;

import com.rslakra.springservices.thymeleafsidebarlayouts.tutorial.persistence.entity.Tutorial;
import com.rslakra.springservices.thymeleafsidebarlayouts.tutorial.service.TutorialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/tutorials")
public class TutorialController {

    private final TutorialService tutorialService;

    /**
     * @param tutorialService
     */
    @Autowired
    public TutorialController(final TutorialService tutorialService) {
        this.tutorialService = tutorialService;
    }


    /**
     * @param tutorial
     * @return
     */
    @PostMapping
    public Tutorial create(@RequestBody Tutorial tutorial) {
        tutorial = tutorialService.create(tutorial);
        return tutorial;
    }

    /**
     * @param tutorials
     * @return
     */
    @PostMapping("/batch")
    public List<Tutorial> batchCreate(@RequestBody List<Tutorial> tutorials) {
        tutorials = tutorialService.create(tutorials);
        return tutorials;
    }

    /**
     * @return
     */
    @GetMapping
    public List<Tutorial> getAll() {
        return tutorialService.getAll();
    }

    /**
     * @param keyword
     * @return
     */
    @GetMapping("/filter")
    public List<Tutorial> getByFilter(@Param("keyword") String keyword) {
        final List<Tutorial> tutorials = new ArrayList<>();
        if (keyword == null) {
            tutorialService.getAll().forEach(tutorials::add);
        } else {
            tutorialService.findByTitleContainingIgnoreCase(keyword).forEach(tutorials::add);
        }

        return tutorials;
    }

    /**
     * @param tutorial
     * @return
     */
    @PutMapping
    public Tutorial update(@RequestBody Tutorial tutorial) {
        tutorial = tutorialService.update(tutorial);
        return tutorial;
    }

    /**
     * @param tutorials
     * @return
     */
    @PutMapping("/batch")
    public List<Tutorial> batchUpdate(@RequestBody List<Tutorial> tutorials) {
        tutorials = tutorialService.update(tutorials);
        return tutorials;
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Tutorial delete(@PathVariable("id") Long id) {
        Tutorial tutorial = tutorialService.getById(id);
        tutorialService.delete(id);
        return tutorial;
    }
}
