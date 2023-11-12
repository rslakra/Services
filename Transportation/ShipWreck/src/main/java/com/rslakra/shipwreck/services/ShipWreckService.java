/*
 * Copyright 2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.rslakra.shipwreck.services;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.spring.exception.InvalidRequestException;
import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.persistence.Operation;
import com.devamatre.framework.spring.service.AbstractService;
import com.rslakra.shipwreck.model.ShipWreck;
import com.rslakra.shipwreck.repository.ShipWreckRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
@Service
public class ShipWreckService implements AbstractService<ShipWreck, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipWreckService.class);

    private final ShipWreckRepository shipWreckRepository;

    /**
     * @param shipWreckRepository
     */
    @Autowired
    public ShipWreckService(ShipWreckRepository shipWreckRepository) {
        LOGGER.debug("ShipWreckService({})", shipWreckRepository);
        this.shipWreckRepository = shipWreckRepository;
    }

    /**
     * @param operation
     * @param shipWreck
     * @return
     */
    @Override
    public ShipWreck validate(Operation operation, ShipWreck shipWreck) {
        switch (operation) {
            case CREATE:
                if (BeanUtils.isEmpty(shipWreck)) {
                    throw new InvalidRequestException("The shipWreck should provide!");
                } else if (BeanUtils.isEmpty(shipWreck.getName())) {
                    throw new InvalidRequestException("The shipWreck's name should provide!");
                } else if (BeanUtils.isEmpty(shipWreck.getCondition())) {
                    throw new InvalidRequestException("The shipWreck's condition should provide!");
                } else if (BeanUtils.isEmpty(shipWreck.getDepth())) {
                    throw new InvalidRequestException("The shipWreck's depth should provide!");
                } else if (BeanUtils.isEmpty(shipWreck.getLatitude())) {
                    throw new InvalidRequestException("The shipWreck's latitude should provide!");
                } else if (BeanUtils.isEmpty(shipWreck.getLongitude())) {
                    throw new InvalidRequestException("The shipWreck's longitude should provide!");
                }

                break;

            case UPDATE:
                if (BeanUtils.isEmpty(shipWreck)) {
                    throw new InvalidRequestException("The shipWreck should provide!");
                } else if (BeanUtils.isNull(shipWreck.getId())) {
                    throw new InvalidRequestException("The shipWreck's ID should provide!");
                }

                break;

            default:
                break;
        }

        return shipWreck;
    }

    /**
     * @param shipWreck
     * @return
     */
    @Override
    public ShipWreck create(ShipWreck shipWreck) {
        LOGGER.debug("+create({})", shipWreck);
        validate(Operation.CREATE, shipWreck);
        shipWreck = shipWreckRepository.save(shipWreck);
        LOGGER.debug("-create(), shipWreck:{}", shipWreck);
        return shipWreck;
    }

    /**
     * @param shipWrecks
     * @return
     */
    @Override
    public List<ShipWreck> create(List<ShipWreck> shipWrecks) {
        LOGGER.debug("+create({})", shipWrecks);
        shipWrecks.forEach(shipWreck -> validate(Operation.CREATE, shipWreck));
        shipWrecks = shipWreckRepository.saveAll(shipWrecks);
        LOGGER.debug("-create(), shipWrecks:{}", shipWrecks);
        return shipWrecks;
    }

    /**
     * @return
     */
    @Override
    public List<ShipWreck> getAll() {
        LOGGER.debug("+getAll()");
        List<ShipWreck> shipWrecks = shipWreckRepository.findAll();
        LOGGER.debug("-getAll(), shipWrecks:{}", shipWrecks);
        return shipWrecks;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ShipWreck getById(final Long id) {
        LOGGER.debug("+getById({})", id);
        ShipWreck
            shipWreck =
            shipWreckRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%s", id));
        LOGGER.debug("-getById(), shipWreck:{}", shipWreck);
        return shipWreck;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<ShipWreck> getByFilter(Filter filter) {
        return shipWreckRepository.findAll();
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<ShipWreck> getByFilter(Filter filter, Pageable pageable) {
        return null;
    }

    /**
     * @param shipWreck
     * @return
     */
    @Override
    public ShipWreck update(ShipWreck shipWreck) {
        LOGGER.debug("+update({})", shipWreck);
        validate(Operation.UPDATE, shipWreck);
        ShipWreck oldShipWreck = getById(shipWreck.getId());
        BeanUtils.copyProperties(shipWreck, oldShipWreck);
        shipWreck = shipWreckRepository.save(oldShipWreck);
        LOGGER.debug("-update(), shipWreck:{}", shipWreck);
        return shipWreck;
    }

    /**
     * @param shipWrecks
     * @return
     */
    @Override
    public List<ShipWreck> update(List<ShipWreck> shipWrecks) {
        LOGGER.debug("+update({})", shipWrecks);
        shipWrecks.forEach(shipWreck -> validate(Operation.UPDATE, shipWreck));
        shipWrecks = shipWreckRepository.saveAll(shipWrecks);
        LOGGER.debug("-update(), shipWrecks:{}", shipWrecks);
        return shipWrecks;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public ShipWreck delete(Long id) {
        LOGGER.debug("+delete({})", id);
        ShipWreck shipWreck = getById(id);
        shipWreckRepository.delete(shipWreck);
        LOGGER.debug("-delete(), shipWreck:{}", shipWreck);
        return shipWreck;
    }

}
