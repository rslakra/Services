package com.rslakra.springservices.thymeleafsidebarlayouts.home.service;

import com.rslakra.frameworks.spring.exception.InvalidRequestException;
import com.rslakra.frameworks.spring.exception.NoRecordFoundException;
import com.rslakra.springservices.thymeleafsidebarlayouts.framework.service.AbstractServiceImpl;
import com.rslakra.springservices.thymeleafsidebarlayouts.home.persistence.entity.Lead;
import com.rslakra.springservices.thymeleafsidebarlayouts.home.persistence.repository.LeadRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Rohtash Lakra
 * @created 1/27/23 11:43 AM
 */
@Service
public class LeadService extends AbstractServiceImpl<Lead> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LeadService.class);

    private final LeadRepository leadRepository;

    /**
     * @param leadRepository
     */
    @Autowired
    public LeadService(final LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Lead getById(final Long id) {
        return leadRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id: %d", id));
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param lead
     * @return
     */
    @Override
    public Lead validate(Lead lead) {
        if (Objects.nonNull(lead)) {
            if (Objects.isNull(lead.getEmail())) {
                throw new InvalidRequestException("The lead email should provide!");
            } else if (Objects.isNull(lead.getFirstName())) {
                throw new InvalidRequestException("The lead firstName should provide!");
            } else if (Objects.isNull(lead.getLastName())) {
                throw new InvalidRequestException("The lead lastName should provide!");
            } else if (Objects.isNull(lead.getCountry())) {
                throw new InvalidRequestException("The lead country should provide!");
            } else if (Objects.isNull(lead.getMessage())) {
                throw new InvalidRequestException("The lead message should provide!");
            }
        }

        return lead;
    }

    /**
     * Creates the <code>T</code> object.
     *
     * @param lead
     * @return
     */
    @Override
    public Lead create(Lead lead) {
        validate(lead);
        return leadRepository.save(lead);
    }

    /**
     * Creates the <code>List<T></code> objects.
     *
     * @param leads
     * @return
     */
    @Override
    public List<Lead> create(List<Lead> leads) {
        final List<Lead> leadSaved = new ArrayList<>();
        leads.forEach(lead -> {
            try {
                leadSaved.add(create(lead));
            } catch (Exception ex) {
                LOGGER.error(ex.getLocalizedMessage(), ex);
            }
        });

        return leadSaved;
    }

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    @Override
    public List<Lead> getAll() {
        return leadRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @return
     */
    @Override
    public List<Lead> getByFilter() {
        return leadRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Lead> getByFilter(Pageable pageable) {
        return leadRepository.findAll(pageable);
    }

    /**
     * Updates the <code>T</code> object.
     *
     * @param lead
     * @return
     */
    @Override
    public Lead update(Lead lead) {
        validate(lead);
        Lead oldLead = getById(lead.getId());
        BeanUtils.copyProperties(lead, oldLead);
        lead = leadRepository.save(lead);
        return lead;
    }

    /**
     * Updates the <code>List<T></code> objects.
     *
     * @param leads
     * @return
     */
    @Override
    public List<Lead> update(List<Lead> leads) {
        final List<Lead> leadSaved = new ArrayList<>();
        leads.forEach(lead -> {
            try {
                leadSaved.add(update(lead));
            } catch (Exception ex) {
                LOGGER.error(ex.getLocalizedMessage(), ex);
            }
        });

        return leadSaved;
    }

    /**
     * Deletes the object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Lead delete(Long id) {
        Lead lead = getById(id);
        leadRepository.deleteById(id);
        return lead;
    }

}
