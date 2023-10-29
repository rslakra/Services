package com.rslakra.iws.businessservice.marketing.controller;

import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.controller.rest.AbstractRestController;
import com.devamatre.framework.spring.filter.Filter;
import com.rslakra.iws.businessservice.marketing.filter.MarketingFilter;
import com.rslakra.iws.businessservice.marketing.persistence.entity.Marketing;
import com.rslakra.iws.businessservice.marketing.service.MarketingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 5/25/22 5:08 PM
 */
@RestController
@RequestMapping("${restPrefix}/marketing")
public class MarketingController extends AbstractRestController<Marketing, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketingController.class);

    private final MarketingService marketingService;

    /**
     * @param marketingService
     */
    @Autowired
    public MarketingController(final MarketingService marketingService) {
        this.marketingService = marketingService;
    }

    /**
     * Returns the list of <code>T</code> objects.
     *
     * @return
     */
    @GetMapping
    @Override
    public List<Marketing> getAll() {
        return marketingService.getAll();
    }

    /**
     * Returns the list of <code>T</code> filters objects.
     *
     * @param allParams
     * @return
     */
    @GetMapping("/filter")
    @Override
    public List<Marketing> getByFilter(@RequestParam Map<String, Object> allParams) {
        LOGGER.debug("+getByFilter({})", allParams);
        List<Marketing> marketings = Collections.emptyList();
        MarketingFilter marketingFilter = new MarketingFilter(allParams);
        if (marketingFilter.hasKeys(MarketingFilter.ID, MarketingFilter.FIRST_NAME)) {
        } else if (marketingFilter.hasKey(MarketingFilter.ID)) {
            marketings = Arrays.asList(marketingService.getById(marketingFilter.getLong(MarketingFilter.ID)));
        } else {
            marketings = marketingService.getAll();
        }

        LOGGER.debug("-getByFilter(), marketings: {}", marketings);
        return marketings;
    }

    /**
     * Returns the <code>Page<T></code> list of objects filtered with <code>allParams</code>.
     *
     * @param allParams @return
     * @param pageable
     */
    @GetMapping("/pageable")
    @Override
    public Page<Marketing> getByFilter(Map<String, Object> allParams, Pageable pageable) {
        return marketingService.getByFilter(null, pageable);
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<Marketing> getByFilter(Filter filter) {
        return null;
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Marketing> getByFilter(Filter filter, Pageable pageable) {
        return null;
    }

    /**
     * Creates the <code>T</code> type object.
     *
     * @param task
     * @return
     */
    @PostMapping
    @ResponseBody
    @Override
    public ResponseEntity<Marketing> create(@Validated @RequestBody Marketing task) {
        task = marketingService.create(task);
        return ResponseEntity.ok(task);
    }

    /**
     * Creates the list of <code>T</code> type objects.
     *
     * @param tasks
     * @return
     */
    @PostMapping("/batch")
    @ResponseBody
    @Override
    public ResponseEntity<List<Marketing>> create(@Validated @RequestBody List<Marketing> tasks) {
        tasks = marketingService.create(tasks);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Updates the <code>T</code> type object.
     *
     * @param task
     * @return
     */
    @PutMapping
    @Override
    public ResponseEntity<Marketing> update(@Validated @RequestBody Marketing task) {
        task = marketingService.update(task);
        return ResponseEntity.ok(task);
    }

    /**
     * Updates the list of <code>T</code> type objects.
     *
     * @param tasks
     * @return
     */
    @PutMapping("/batch")
    @Override
    public ResponseEntity<List<Marketing>> update(@Validated @RequestBody List<Marketing> tasks) {
        tasks = marketingService.update(tasks);
        return ResponseEntity.ok(tasks);
    }

    /**
     * Deletes the <code>T</code> type object by <code>id</code>.
     *
     * @param idOptional
     * @return
     */
    @DeleteMapping("/{id}")
    @Override
    public ResponseEntity<Payload> delete(@PathVariable(value = "id") Optional<Long> idOptional) {
        validate(idOptional);
        marketingService.delete(idOptional.get());
        Payload payload = Payload.newBuilder()
            .withDeleted(Boolean.TRUE)
            .withMessage("Record with id:%d deleted successfully!", idOptional.get());
        return ResponseEntity.ok(payload);
    }

    /**
     * Uploads the <code>file</code>
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @Override
    public ResponseEntity<Payload> upload(MultipartFile file) {
        return null;
    }

    /**
     * Downloads the object of <code>T</code> as <code>fileType</code> file.
     *
     * @param fileType
     * @return
     */
    @GetMapping("/download")
    @Override
    public ResponseEntity<Resource> download(String fileType) {
        return null;
    }

}
