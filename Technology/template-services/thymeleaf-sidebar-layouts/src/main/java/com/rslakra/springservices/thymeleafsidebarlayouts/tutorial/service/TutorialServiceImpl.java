package com.rslakra.springservices.thymeleafsidebarlayouts.tutorial.service;

import com.rslakra.frameworks.spring.exception.NoRecordFoundException;
import com.rslakra.springservices.thymeleafsidebarlayouts.framework.service.AbstractServiceImpl;
import com.rslakra.springservices.thymeleafsidebarlayouts.tutorial.persistence.entity.Tutorial;
import com.rslakra.springservices.thymeleafsidebarlayouts.tutorial.persistence.repository.TutorialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 1/27/23 12:09 PM
 */
@Service
public class TutorialServiceImpl extends AbstractServiceImpl<Tutorial> implements TutorialService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TutorialServiceImpl.class);

    private final TutorialRepository tutorialRepository;

    /**
     * @param tutorialRepository
     */
    @Autowired
    public TutorialServiceImpl(final TutorialRepository tutorialRepository) {
        this.tutorialRepository = tutorialRepository;
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Tutorial getById(final Long id) {
        return tutorialRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%d", id));
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param tutorial
     * @return
     */
    @Override
    public Tutorial validate(Tutorial tutorial) {
        return tutorial;
    }

    /**
     * Creates the <code>T</code> object.
     *
     * @param tutorial
     * @return
     */
    @Override
    public Tutorial create(Tutorial tutorial) {
        tutorial = tutorialRepository.save(tutorial);
        return tutorial;
    }

    /**
     * Creates the <code>List<T></code> objects.
     *
     * @param tutorials
     * @return
     */
    @Override
    public List<Tutorial> create(List<Tutorial> tutorials) {
        final List<Tutorial> savedTutorials = new ArrayList<>();
        tutorials.forEach(tutorial -> {
            try {
                savedTutorials.add(create(tutorial));
            } catch (Exception ex) {
                LOGGER.error(ex.getLocalizedMessage(), ex);
            }
        });

        return savedTutorials;
    }

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    @Override
    public List<Tutorial> getAll() {
        return tutorialRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @return
     */
    @Override
    public List<Tutorial> getByFilter() {
        return tutorialRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param pageable
     * @return
     */
    @Override
    public Page<Tutorial> getByFilter(Pageable pageable) {
        return tutorialRepository.findAll(pageable);
    }

    /**
     * Updates the <code>T</code> object.
     *
     * @param tutorial
     * @return
     */
    @Override
    public Tutorial update(Tutorial tutorial) {
        Tutorial oldTutorial = getById(tutorial.getId());
        BeanUtils.copyProperties(tutorial, oldTutorial);
        tutorial = tutorialRepository.save(oldTutorial);
        return tutorial;
    }

    /**
     * Updates the <code>List<T></code> objects.
     *
     * @param tutorials
     * @return
     */
    @Override
    public List<Tutorial> update(List<Tutorial> tutorials) {
        final List<Tutorial> savedTutorials = new ArrayList<>();
        tutorials.forEach(tutorial -> {
            try {
                savedTutorials.add(update(tutorial));
            } catch (Exception ex) {
                LOGGER.error(ex.getLocalizedMessage(), ex);
            }
        });

        return savedTutorials;
    }

    /**
     * Deletes the object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Tutorial delete(Long id) {
        Tutorial tutorial = getById(id);
        tutorialRepository.deleteById(id);
        return tutorial;
    }

    /**
     * @param keyword
     * @return
     */
    @Override
    public List<Tutorial> findByTitleContainingIgnoreCase(String keyword) {
        return tutorialRepository.findByTitleContainingIgnoreCase(keyword);
    }

    /**
     * @param id
     * @param isPublished
     */
    @Override
    public void updatePublishedStatus(Long id, boolean isPublished) {
        tutorialRepository.updatePublishedStatus(id, isPublished);
    }
}
