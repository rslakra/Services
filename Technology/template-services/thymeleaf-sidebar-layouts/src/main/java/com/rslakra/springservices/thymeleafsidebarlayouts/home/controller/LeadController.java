package com.rslakra.springservices.thymeleafsidebarlayouts.home.controller;

import com.rslakra.springservices.thymeleafsidebarlayouts.home.persistence.entity.Lead;
import com.rslakra.springservices.thymeleafsidebarlayouts.home.service.LeadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 2/9/23 3:49 PM
 */
@RestController
@RequestMapping(value = "${apiPrefix}/leads", produces = MediaType.APPLICATION_JSON_VALUE)
public class LeadController {

    private final LeadService leadService;

    /**
     * @param leadService
     */
    @Autowired
    public LeadController(final LeadService leadService) {
        this.leadService = leadService;
    }

    /**
     * @param lead
     * @return
     */
    @PostMapping
    public Lead create(@RequestBody Lead lead) {
        lead = leadService.create(lead);
        return lead;
    }

    /**
     * @param leads
     * @return
     */
    @PostMapping("/batch")
    public List<Lead> batchCreate(@RequestBody List<Lead> leads) {
        leads = leadService.create(leads);
        return leads;
    }

    /**
     * @return
     */
    @GetMapping
    public List<Lead> getAll() {
        return leadService.getAll();
    }

    /**
     * @param email
     * @param firstName
     * @param lastName
     * @param country
     * @return
     */
    @GetMapping("/filter")
    public List<Lead> getByFilter(@Param("email") String email, @Param("firstName") String firstName,
                                  @Param("lastName") String lastName, @Param("country") String country) {
        final List<Lead> leads = leadService.getByFilter();
        return leads;
    }

    /**
     * @param lead
     * @return
     */
    @PutMapping
    public Lead update(@RequestBody Lead lead) {
        lead = leadService.update(lead);
        return lead;
    }

    /**
     * @param leads
     * @return
     */
    @PutMapping("/batch")
    public List<Lead> batchUpdate(@RequestBody List<Lead> leads) {
        leads = leadService.update(leads);
        return leads;
    }

    /**
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Lead delete(@PathVariable("id") Long id) {
        Lead lead = leadService.getById(id);
        leadService.delete(id);
        return lead;
    }

}
