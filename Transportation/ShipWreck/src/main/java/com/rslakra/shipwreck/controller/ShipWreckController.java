package com.rslakra.shipwreck.controller;

import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.controller.rest.AbstractRestController;
import com.devamatre.framework.spring.filter.Filter;
import com.rslakra.shipwreck.filter.FilterCriteria;
import com.rslakra.shipwreck.filter.FilterOperation;
import com.rslakra.shipwreck.filter.ShipWreckFilter;
import com.rslakra.shipwreck.model.ShipWreck;
import com.rslakra.shipwreck.services.ShipWreckService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Rohtash Singh Lakra
 * @version 1.0.0
 */
@RestController
@RequestMapping("${restPrefix}/shipwrecks")
public class ShipWreckController extends AbstractRestController<ShipWreck, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ShipWreckController.class);
    private final ShipWreckService shipWreckService;

    /**
     * @param shipWreckService
     */
    @Autowired
    public ShipWreckController(ShipWreckService shipWreckService) {
        this.shipWreckService = shipWreckService;
    }

    /**
     * @return
     */
    @GetMapping
    @Override
    public List<ShipWreck> getAll() {
        LOGGER.debug("getAll()");
        List<ShipWreck> shipWrecks = shipWreckService.getAll();
        LOGGER.debug("getAll(), shipWrecks: {}", shipWrecks);
        return shipWrecks;
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<ShipWreck> getByFilter(Filter filter) {
        LOGGER.debug("getByFilter({})", filter);
        return null;
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<ShipWreck> getByFilter(Filter filter, Pageable pageable) {
        LOGGER.debug("getByFilter({}, {})", filter, pageable);
        return null;
    }

    /**
     * @param allParams
     * @return
     */
    @GetMapping("/filter")
    @Override
    public List<ShipWreck> getByFilter(@RequestParam Map<String, Object> allParams) {
        LOGGER.debug("+getByFilter({})", allParams);
        List<ShipWreck> shipWrecks = Collections.emptyList();
        ShipWreckFilter filter = new ShipWreckFilter(allParams);
        if (filter.hasKeys(ShipWreckFilter.ID, ShipWreckFilter.NAME)) {
            // find by ID and Name
        } else if (filter.hasKey(ShipWreckFilter.ID)) {
            FilterCriteria filterCriteria = FilterCriteria.builder()
                .key(ShipWreckFilter.ID)
                .operation(FilterOperation.EQUALS.name())
                .value(filter.getLong(ShipWreckFilter.ID))
                .build();
            filter.setCriteria(filterCriteria);

            ShipWreck shipWreck = shipWreckService.getById(filter.getLong(ShipWreckFilter.ID));
            shipWrecks = Arrays.asList(shipWreck);
        } else if (filter.hasKey(ShipWreckFilter.NAME)) {
//            shipWrecks = Arrays.asList(shipWreckService.getById(filter.getValue(ShipWreckFilter.NAME)));
        } else {
            shipWrecks = shipWreckService.getByFilter(filter);
        }

        LOGGER.debug("-getByFilter(), shipWrecks: {}", shipWrecks);
        return shipWrecks;
    }

    /**
     * @param allParams
     * @param pageable
     * @return
     */
    @Override
    public Page<ShipWreck> getByFilter(Map<String, Object> allParams, Pageable pageable) {
        LOGGER.debug("+getByFilter({}, {})", allParams, pageable);
        return null;
    }

    /**
     * @param shipWreck
     * @return
     */
    @PostMapping
    @Override
    public ResponseEntity<ShipWreck> create(@RequestBody ShipWreck shipWreck) {
        LOGGER.debug("+create({})", shipWreck);
        shipWreck = shipWreckService.create(shipWreck);
        ResponseEntity<ShipWreck> responseEntity = ResponseEntity.ok(shipWreck);
        LOGGER.debug("-create(), responseEntity: {}", responseEntity);
        return responseEntity;
    }

    /**
     * @param shipWrecks
     * @return
     */
    @PostMapping("/batch")
    @Override
    public ResponseEntity<List<ShipWreck>> create(@RequestBody List<ShipWreck> shipWrecks) {
        LOGGER.debug("+create({})", shipWrecks);
        shipWrecks = shipWreckService.create(shipWrecks);
        ResponseEntity<List<ShipWreck>> responseEntity = ResponseEntity.ok(shipWrecks);
        LOGGER.debug("-create(), responseEntity: {}", responseEntity);
        return responseEntity;
    }

    /**
     * @param shipWreck
     * @return
     */
    @PutMapping
    @Override
    public ResponseEntity<ShipWreck> update(@RequestBody ShipWreck shipWreck) {
        LOGGER.debug("+update({})", shipWreck);
        shipWreck = shipWreckService.update(shipWreck);
        ResponseEntity<ShipWreck> responseEntity = ResponseEntity.ok(shipWreck);
        LOGGER.debug("-update(), responseEntity: {}", responseEntity);
        return responseEntity;
    }

    /**
     * @param shipWrecks
     * @return
     */
    @PutMapping("/batch")
    @Override
    public ResponseEntity<List<ShipWreck>> update(@RequestBody List<ShipWreck> shipWrecks) {
        LOGGER.debug("+update({})", shipWrecks);
        shipWrecks = shipWreckService.update(shipWrecks);
        ResponseEntity<List<ShipWreck>> responseEntity = ResponseEntity.ok(shipWrecks);
        LOGGER.debug("-update(), responseEntity: {}", responseEntity);
        return responseEntity;
    }

    /**
     * @param idOptional
     * @return
     */
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Payload> delete(@PathVariable(name = "id") Optional<Long> idOptional) {
        LOGGER.debug("+delete({})", idOptional);
        ShipWreck shipWreck = shipWreckService.delete(idOptional.get());
        Payload payload = Payload.newBuilder().withMessage("Deleted with id=%d!", shipWreck.getId());
        ResponseEntity<Payload> responseEntity = ResponseEntity.ok(payload);
        LOGGER.debug("-delete(), responseEntity: {}", responseEntity);
        return responseEntity;
    }

    /**
     * @param file
     * @return
     */
    @Override
    public ResponseEntity<Payload> upload(@RequestBody MultipartFile file) {
        LOGGER.debug("upload({})", file);
        return null;
    }

    /**
     * @param fileType
     * @return
     */
    @Override
    public ResponseEntity<Resource> download(@RequestParam("fileType") String fileType) {
        LOGGER.debug("download({})", fileType);
        return null;
    }

}
