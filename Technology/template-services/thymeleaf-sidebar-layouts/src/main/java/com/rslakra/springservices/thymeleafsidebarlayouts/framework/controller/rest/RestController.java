package com.rslakra.springservices.thymeleafsidebarlayouts.framework.controller.rest;

import com.rslakra.frameworks.spring.exception.InvalidRequestException;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @author Rohtash Lakra
 * @created 2/8/23 1:33 PM
 */
public interface RestController<T> {

    /**
     * @param id
     */
    public default void validate(Optional<Long> id) {
        if (!id.isPresent()) {
            throw new InvalidRequestException("The ID should provide!");
        }
    }

    /**
     * Returns the list of all <code>T</code> object.
     *
     * @return
     */
    public List<T> getAll();

    /**
     * Returns the list of <code>T</code> filters objects.
     *
     * @param allParams
     * @return
     */
    public List<T> getByFilter(Map<String, String> allParams);

    /**
     * Returns the <code>Page<T></code> list of objects filtered with <code>allParams</code>.
     *
     * @param allParams@return
     */
    public Page<T> getByFilter(Map<String, String> allParams, Pageable pageable);

    /**
     * Creates the <code>T</code> type object.
     *
     * @param t
     * @return
     */
    public ResponseEntity<T> create(T t);

    /**
     * Creates the list of <code>T</code> type objects.
     *
     * @param ts
     * @return
     */
    public ResponseEntity<List<T>> create(List<T> ts);

    /**
     * Updates the <code>T</code> type object.
     *
     * @param t
     * @return
     */
    public ResponseEntity<T> update(T t);

    /**
     * Updates the list of <code>T</code> type objects.
     *
     * @param ts
     * @return
     */
    public ResponseEntity<List<T>> update(List<T> ts);


    /**
     * Deletes the <code>T</code> type object by <code>id</code>.
     *
     * @param idOptional
     * @return
     */
    public ResponseEntity<?> delete(Optional<Long> idOptional);

    /**
     * Uploads the <code>file</code>
     *
     * @param file
     * @return
     */
    public ResponseEntity<?> upload(MultipartFile file);

    /**
     * Downloads the object of <code>T</code> as <code>fileType</code> file.
     *
     * @param fileType
     * @return
     */
    public ResponseEntity<Resource> download(String fileType);

}
