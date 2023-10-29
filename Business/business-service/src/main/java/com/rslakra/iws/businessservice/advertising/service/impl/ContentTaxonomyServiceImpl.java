package com.rslakra.iws.businessservice.advertising.service.impl;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.frameworks.spring.exception.InvalidRequestException;
import com.rslakra.frameworks.spring.exception.NoRecordFoundException;
import com.rslakra.frameworks.spring.filter.Filter;
import com.rslakra.frameworks.spring.persistence.Operation;
import com.rslakra.frameworks.spring.service.AbstractServiceImpl;
import com.rslakra.iws.businessservice.account.persistence.repository.UserRepository;
import com.rslakra.iws.businessservice.advertising.persistence.entity.ContentTaxonomy;
import com.rslakra.iws.businessservice.advertising.persistence.repository.ContentTaxonomyRepository;
import com.rslakra.iws.businessservice.advertising.service.ContentTaxonomyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 10:06 AM
 */
@Service
public class ContentTaxonomyServiceImpl extends AbstractServiceImpl<ContentTaxonomy> implements ContentTaxonomyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ContentTaxonomyServiceImpl.class);

    private final UserRepository userRepository;
    private final ContentTaxonomyRepository contentTaxonomyRepository;

    /**
     * @param userRepository
     * @param contentTaxonomyRepository
     */
    @Autowired
    public ContentTaxonomyServiceImpl(final UserRepository userRepository,
                                      final ContentTaxonomyRepository contentTaxonomyRepository) {
        LOGGER.debug("ContentTaxonomyServiceImpl({}, {})", userRepository, contentTaxonomyRepository);
        this.userRepository = userRepository;
        this.contentTaxonomyRepository = contentTaxonomyRepository;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param contentTaxonomy
     * @return
     */
    @Override
    public ContentTaxonomy validate(Operation operation, ContentTaxonomy contentTaxonomy) {
        LOGGER.debug("+validate({}, {})", operation, contentTaxonomy);
        switch (operation) {
            case CREATE: {
                if (BeanUtils.isEmpty(contentTaxonomy.getName())) {
                    throw new InvalidRequestException("The contentTaxonomy's name should provide!");
                }

                if (BeanUtils.isEmpty(contentTaxonomy.getTier1())) {
                    throw new InvalidRequestException("The contentTaxonomy's tier1 should provide!");
                }

//                // check task exists for this user
//                List<ContentTaxonomy> contentTaxonomyList = contentTaxonomyRepository.findAllByUserId(contentTaxonomy.getUserId());
//                if (contentTaxonomyList.stream()
//                    .anyMatch(oldContentTaxonomy -> oldContentTaxonomy.getName().equals(contentTaxonomy.getName()))) {
//                    throw new DuplicateRecordException("contentTaxonomy:%s for userId:%d", contentTaxonomy.getName(),
//                                                       contentTaxonomy.getUserId());
//                }
            }

            break;

            case UPDATE: {
                if (BeanUtils.isNull(contentTaxonomy.getId())) {
                    throw new InvalidRequestException("The contentTaxonomy's ID should provide!");
                }

//                if (BeanUtils.isNotEmpty(contentTaxonomy.getUserId())) {
//                    Optional<ContentTaxonomy>
//                        contentTaxonomyOptional =
//                        contentTaxonomyRepository.findByIdAndUserId(contentTaxonomy.getId(),
//                                                                    contentTaxonomy.getUserId());
//                    if (!contentTaxonomyOptional.isPresent()) {
//                        throw new InvalidRequestException("The contentTaxonomy does not belong to user: %d",
//                                                          contentTaxonomy.getUserId());
//                    }
//                }
            }

            break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        LOGGER.debug("-validate(), contentTaxonomy: {}", contentTaxonomy);
        return contentTaxonomy;
    }

    /**
     * Creates the <code>T</code> object.
     *
     * @param contentTaxonomy
     * @return
     */
    @Override
    public ContentTaxonomy create(ContentTaxonomy contentTaxonomy) {
        LOGGER.debug("+create({})", contentTaxonomy);
        contentTaxonomy = validate(Operation.CREATE, contentTaxonomy);
        contentTaxonomy = contentTaxonomyRepository.save(contentTaxonomy);
        LOGGER.debug("-create(), contentTaxonomy: {}", contentTaxonomy);
        return contentTaxonomy;
    }

    /**
     * Creates the <code>List<T></code> objects.
     *
     * @param contentTaxonomys
     * @return
     */
    @Override
    public List<ContentTaxonomy> create(List<ContentTaxonomy> contentTaxonomys) {
        LOGGER.debug("+create({})", contentTaxonomys);
        if (BeanUtils.isEmpty(contentTaxonomys)) {
            throw new InvalidRequestException("The contentTaxonomy should provide!");
        }

        contentTaxonomys.forEach(contentTaxonomy -> validate(Operation.CREATE, contentTaxonomy));
        contentTaxonomys = contentTaxonomyRepository.saveAll(contentTaxonomys);

        LOGGER.debug("-create(), marketing: {}", contentTaxonomys);
        return contentTaxonomys;
    }

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    @Override
    public List<ContentTaxonomy> getAll() {
        return contentTaxonomyRepository.findAll();
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public ContentTaxonomy getById(final Long id) {
        return contentTaxonomyRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%d", id));
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @return
     */
    @Override
    public List<ContentTaxonomy> getByFilter(Filter filter) {
        return contentTaxonomyRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<ContentTaxonomy> getByFilter(Filter filter, Pageable pageable) {
        return contentTaxonomyRepository.findAll(pageable);
    }

    /**
     * Updates the <code>T</code> object.
     *
     * @param contentTaxonomy
     * @return
     */
    @Override
    public ContentTaxonomy update(ContentTaxonomy contentTaxonomy) {
        LOGGER.debug("+update({})", contentTaxonomy);
        contentTaxonomy = validate(Operation.UPDATE, contentTaxonomy);
        contentTaxonomy = contentTaxonomyRepository.save(contentTaxonomy);
        LOGGER.debug("-update(), contentTaxonomy: {}", contentTaxonomy);
        return contentTaxonomy;
    }

    /**
     * Updates the <code>List<T></code> objects.
     *
     * @param contentTaxonomys
     * @return
     */
    @Override
    public List<ContentTaxonomy> update(List<ContentTaxonomy> contentTaxonomys) {
        LOGGER.debug("+update({})", contentTaxonomys);
        if (BeanUtils.isEmpty(contentTaxonomys)) {
            throw new InvalidRequestException("The marketing should provide!");
        }

        contentTaxonomys.forEach(contentTaxonomy -> validate(Operation.UPDATE, contentTaxonomy));
        contentTaxonomys = contentTaxonomyRepository.saveAll(contentTaxonomys);

        LOGGER.debug("-update(), marketing: {}", contentTaxonomys);
        return contentTaxonomys;
    }

    /**
     * Deletes the object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public ContentTaxonomy delete(Long id) {
        LOGGER.debug("+delete({})", id);
        BeanUtils.assertNonNull(id, "The contentTaxonomy's ID should provide!");
        ContentTaxonomy contentTaxonomy = getById(id);
        LOGGER.info("Deleting {}", contentTaxonomy);
        contentTaxonomyRepository.delete(contentTaxonomy);
        LOGGER.debug("-delete(), contentTaxonomy: {}", contentTaxonomy);
        return contentTaxonomy;
    }
}
