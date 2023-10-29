package com.rslakra.services.automobile.controller.rest;

import com.devamatre.framework.core.Payload;
import com.devamatre.framework.spring.controller.rest.AbstractRestController;
import com.devamatre.framework.spring.filter.Filter;
import com.rslakra.services.automobile.domain.entities.Appointment;
import com.rslakra.services.automobile.filter.AppointmentFilter;
import com.rslakra.services.automobile.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
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
 * @author Rohtash Lakra
 * @created 4/26/23 11:53 AM
 */
@RestController
@RequestMapping(value = "${restPrefix}/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
public class AppointmentRestController extends AbstractRestController<Appointment, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentRestController.class);

    private final AppointmentService appointmentService;

    /**
     * @param appointmentService
     */
    @Autowired
    public AppointmentRestController(AppointmentService appointmentService) {
        LOGGER.debug("AppointmentRestController({})", appointmentService);
        this.appointmentService = appointmentService;
    }

    /**
     * @return
     */
    @GetMapping
    @Override
    public List<Appointment> getAll() {
        return appointmentService.getAll();
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<Appointment> getByFilter(Filter filter) {
        return null;
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Appointment> getByFilter(Filter filter, Pageable pageable) {
        return null;
    }

    /**
     * @param allParams
     * @return
     */
    @GetMapping("/filter")
    @Override
    public List<Appointment> getByFilter(@RequestParam Map<String, Object> allParams) {
        LOGGER.debug("+getByFilter({})", allParams);
        List<Appointment> appointments = Collections.emptyList();
        AppointmentFilter filter = new AppointmentFilter(allParams);
        if (filter.hasKeys(AppointmentFilter.ID, AppointmentFilter.NAME)) {
        } else if (filter.hasKey(AppointmentFilter.ID)) {
            appointments = Arrays.asList(appointmentService.getById(filter.getLong(AppointmentFilter.ID)));
        } else if (filter.hasKey(AppointmentFilter.NAME)) {
//            roles = Arrays.asList(appointmentService.getByName(filter.getValue(AppointmentFilter.NAME)));
        } else {
            appointments = appointmentService.getByFilter(filter);
        }

        LOGGER.debug("-getByFilter(), appointments: {}", appointments);
        return appointments;
    }

    /**
     * @param allParams
     * @param pageable
     * @return
     */
    @GetMapping("/pageable")
    @Override
    public Page<Appointment> getByFilter(@RequestParam Map<String, Object> allParams, Pageable pageable) {
        return appointmentService.getByFilter(null, pageable);
    }

    /**
     * @param appointment
     * @return
     */
    @PostMapping
    @Override
    public ResponseEntity<Appointment> create(@RequestBody Appointment appointment) {
        ResponseEntity<Appointment> responseEntity = null;
        appointment = appointmentService.create(appointment);
        responseEntity = ResponseEntity.ok(appointment);
        return responseEntity;
    }

    /**
     * @param appointments
     * @return
     */
    @PostMapping("/batch")
    @Override
    public ResponseEntity<List<Appointment>> create(@RequestBody List<Appointment> appointments) {
        ResponseEntity<List<Appointment>> responseEntity = null;
        appointments = appointmentService.create(appointments);
        responseEntity = ResponseEntity.ok(appointments);
        return responseEntity;
    }

    /**
     * @param appointment
     * @return
     */
    @PutMapping
    @Override
    public ResponseEntity<Appointment> update(@RequestBody Appointment appointment) {
        ResponseEntity<Appointment> responseEntity = null;
        appointment = appointmentService.update(appointment);
        responseEntity = ResponseEntity.ok(appointment);
        return responseEntity;
    }

    /**
     * @param appointments
     * @return
     */
    @PutMapping("/batch")
    @Override
    public ResponseEntity<List<Appointment>> update(@RequestBody List<Appointment> appointments) {
        ResponseEntity<List<Appointment>> responseEntity = null;
        appointments = appointmentService.update(appointments);
        responseEntity = ResponseEntity.ok(appointments);
        return responseEntity;
    }

    /**
     * @param idOptional
     * @return
     */
    @DeleteMapping("{id}")
    @Override
    public ResponseEntity<Payload> delete(@PathVariable("id") Optional<Long> idOptional) {
        ResponseEntity<Payload> responseEntity = null;
        Payload<String, Object> payload = Payload.newBuilder();
        if (idOptional.isPresent()) {
            Appointment appointment = appointmentService.delete(idOptional.get());
            payload.withMessage("Appointment [%d] is deleted successfully!", appointment.getId());
            responseEntity = ResponseEntity.ok(payload);
        } else {
            payload.withMessage("Error while deleting id=%d!", idOptional.get());
            responseEntity = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(payload);
        }

        return responseEntity;
    }

    /**
     * @param file
     * @return
     */
    @PostMapping("/upload")
    @Override
    public ResponseEntity<Payload> upload(@RequestParam("file") MultipartFile file) {
        return null;
    }

    /**
     * @param fileType
     * @return
     */
    @PostMapping("/download")
    @Override
    public ResponseEntity<Resource> download(@RequestParam("fileType") String fileType) {
        return null;
    }

}
