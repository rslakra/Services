package com.rslakra.iws.businessservice.marketing.service.impl;

import com.rslakra.frameworks.core.BeanUtils;
import com.rslakra.frameworks.spring.exception.DuplicateRecordException;
import com.rslakra.frameworks.spring.exception.InvalidRequestException;
import com.rslakra.frameworks.spring.exception.NoRecordFoundException;
import com.rslakra.frameworks.spring.filter.Filter;
import com.rslakra.frameworks.spring.persistence.Operation;
import com.rslakra.frameworks.spring.service.AbstractServiceImpl;
import com.rslakra.iws.businessservice.account.persistence.entity.User;
import com.rslakra.iws.businessservice.account.persistence.repository.UserRepository;
import com.rslakra.iws.businessservice.marketing.persistence.entity.Marketing;
import com.rslakra.iws.businessservice.marketing.persistence.repository.MarketingRepository;
import com.rslakra.iws.businessservice.marketing.service.MarketingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 10:06 AM
 */
@Service
public class MarketingServiceImpl extends AbstractServiceImpl<Marketing> implements MarketingService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketingServiceImpl.class);

    private final UserRepository userRepository;
    private final MarketingRepository marketingRepository;

    /**
     * @param userRepository
     * @param marketingRepository
     */
    @Autowired
    public MarketingServiceImpl(final UserRepository userRepository, final MarketingRepository marketingRepository) {
        LOGGER.debug("MarketingServiceImpl({}, {})", userRepository, marketingRepository);
        this.userRepository = userRepository;
        this.marketingRepository = marketingRepository;
    }

    /**
     * Validates the <code>T</code> object.
     *
     * @param operation
     * @param marketing
     * @return
     */
    @Override
    public Marketing validate(Operation operation, Marketing marketing) {
        LOGGER.debug("+validate({}, {})", operation, marketing);
        switch (operation) {
            case CREATE: {
                if (BeanUtils.isEmpty(marketing.getName())) {
                    throw new InvalidRequestException("The marketing's name should provide!");
                } else if (BeanUtils.isNull(marketing.getUserId())) {
                    throw new InvalidRequestException("The marketing's userId should provide!");
                }

                // validate the user exists
                Optional<User> userOptional = userRepository.findById(marketing.getUserId());
                if (!userOptional.isPresent()) {
                    throw new InvalidRequestException("Invalid userId: %d", marketing.getUserId());
                }

                // check task exists for this user
                List<Marketing> marketingList = marketingRepository.findAllByUserId(marketing.getUserId());
                if (marketingList.stream()
                    .anyMatch(oldMarketing -> oldMarketing.getName().equals(marketing.getName()))) {
                    throw new DuplicateRecordException("marketing:%s for userId:%d", marketing.getName(),
                                                       marketing.getUserId());
                }
            }

            break;

            case UPDATE: {
                if (BeanUtils.isNull(marketing.getId())) {
                    throw new InvalidRequestException("The marketing's ID should provide!");
                }

                if (BeanUtils.isNotEmpty(marketing.getUserId())) {
                    Optional<Marketing>
                        marketingOptional =
                        marketingRepository.findByIdAndUserId(marketing.getId(), marketing.getUserId());
                    if (!marketingOptional.isPresent()) {
                        throw new InvalidRequestException("The marketing does not belong to user: %d",
                                                          marketing.getUserId());
                    }
                }
            }

            break;

            default:
                throw new InvalidRequestException("Unsupported Operation!");
        }

        LOGGER.debug("-validate(), marketing: {}", marketing);
        return marketing;
    }

    /**
     * Creates the <code>T</code> object.
     *
     * @param marketing
     * @return
     */
    @Override
    public Marketing create(Marketing marketing) {
        LOGGER.debug("+create({})", marketing);
        marketing = validate(Operation.CREATE, marketing);
        marketing = marketingRepository.save(marketing);
        LOGGER.debug("-create(), marketing: {}", marketing);
        return marketing;
    }

    /**
     * Creates the <code>List<T></code> objects.
     *
     * @param marketings
     * @return
     */
    @Override
    public List<Marketing> create(List<Marketing> marketings) {
        LOGGER.debug("+create({})", marketings);
        if (BeanUtils.isEmpty(marketings)) {
            throw new InvalidRequestException("The marketing should provide!");
        }

        marketings.forEach(marketing -> validate(Operation.CREATE, marketing));
        marketings = marketingRepository.saveAll(marketings);

        LOGGER.debug("-create(), marketing: {}", marketings);
        return marketings;
    }

    /**
     * Returns the list of all <code>T</code> objects.
     *
     * @return
     */
    @Override
    public List<Marketing> getAll() {
        return marketingRepository.findAll();
    }

    /**
     * Returns the <code>T</code> object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Marketing getById(final Long id) {
        return marketingRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%d", id));
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @return
     */
    @Override
    public List<Marketing> getByFilter(Filter filter) {
        return marketingRepository.findAll();
    }

    /**
     * Returns the pageable <code>T</code> object by <code>pageable</code> filter.
     *
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Marketing> getByFilter(Filter filter, Pageable pageable) {
        return marketingRepository.findAll(pageable);
    }

    /**
     * Updates the <code>T</code> object.
     *
     * @param marketing
     * @return
     */
    @Override
    public Marketing update(Marketing marketing) {
        LOGGER.debug("+update({})", marketing);
        marketing = validate(Operation.UPDATE, marketing);
        marketing = marketingRepository.save(marketing);
        LOGGER.debug("-update(), marketing: {}", marketing);
        return marketing;
    }

    /**
     * Updates the <code>List<T></code> objects.
     *
     * @param marketings
     * @return
     */
    @Override
    public List<Marketing> update(List<Marketing> marketings) {
        LOGGER.debug("+update({})", marketings);
        if (BeanUtils.isEmpty(marketings)) {
            throw new InvalidRequestException("The marketing should provide!");
        }

        marketings.forEach(marketing -> validate(Operation.UPDATE, marketing));
        marketings = marketingRepository.saveAll(marketings);

        LOGGER.debug("-update(), marketing: {}", marketings);
        return marketings;
    }

    /**
     * Deletes the object by <code>id</code>.
     *
     * @param id
     * @return
     */
    @Override
    public Marketing delete(Long id) {
        LOGGER.debug("+delete({})", id);
        BeanUtils.assertNonNull(id, "Marketing ID should provide!");
        Marketing marketing = getById(id);
        LOGGER.info("Deleting {}", marketing);
        marketingRepository.delete(marketing);
        LOGGER.debug("-delete(), marketing: {}", marketing);
        return marketing;
    }
}
