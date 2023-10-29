package com.rslakra.services.automobile.service.impl;

import com.devamatre.framework.core.BeanUtils;
import com.devamatre.framework.core.enums.EntityStatus;
import com.devamatre.framework.spring.exception.AuthenticationException;
import com.devamatre.framework.spring.exception.NoRecordFoundException;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.persistence.Operation;
import com.devamatre.framework.spring.service.AbstractServiceImpl;
import com.rslakra.services.automobile.domain.entities.Appointment;
import com.rslakra.services.automobile.domain.entities.AutoUser;
import com.rslakra.services.automobile.domain.repositories.AppointmentRepository;
import com.rslakra.services.automobile.service.AppointmentService;
import com.rslakra.services.automobile.service.security.context.ContextUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Rohtash Lakra
 * @created 4/20/23 5:59 PM
 */
@Service
public class AppointmentServiceImpl extends AbstractServiceImpl<Appointment, Long> implements AppointmentService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentServiceImpl.class);

    private final AppointmentRepository appointmentRepository;

    /**
     * @param appointmentRepository
     */
    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        LOGGER.debug("AppointmentServiceImpl({})", appointmentRepository);
        this.appointmentRepository = appointmentRepository;
    }

    /**
     * @param operation
     * @param appointment
     * @return
     */
    @Override
    public Appointment validate(Operation operation, Appointment appointment) {
        return null;
    }

    /**
     * @param appointment
     * @return
     */
    @Override
    public Appointment create(Appointment appointment) {
        AutoUser autoUser = (AutoUser) ContextUtils.INSTANCE.getAuthentication().getPrincipal();
        appointment.setUser(autoUser);
        if (BeanUtils.isEmpty(appointment.getStatus())) {
            appointment.setStatus(EntityStatus.INACTIVE.name());
        }
        appointment = appointmentRepository.save(appointment);
        return appointment;
    }

    /**
     * @param appointments
     * @return
     */
    @Override
    public List<Appointment> create(List<Appointment> appointments) {
        final List<Appointment> appointmentList = new ArrayList<>();
        appointments.forEach(appointment -> appointmentList.add(create(appointment)));
        return appointmentList;
    }

    /**
     * @return
     */
    @Override
    public List<Appointment> getAll() {
        LOGGER.debug("+getAll()");
        AutoUser autoUser = ContextUtils.getLoggedInUser();
        if (BeanUtils.isNull(autoUser)) {
            throw new AuthenticationException();
        }

        List<Appointment> appointments;
        try {
            appointments = appointmentRepository.findAll();
        } catch (Exception ex) {
            LOGGER.error(ex.getLocalizedMessage(), ex);
            appointments = new ArrayList<>();
            Appointment appointment = new Appointment();
            appointment.setId(1L);
            appointment.addService("Oil Change");
            appointment.addService("Break Oil Change");
            appointment.setUser(autoUser);
//            appointment.setAppointmentOn(LocalDate.now());
            appointment.setStatus("Active");
            appointments.add(appointment);
        }

        LOGGER.debug("+getAll(), appointments: {}", appointments);
        return appointments;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Appointment getById(Long id) {
        return appointmentRepository.findById(id).orElseThrow(() -> new NoRecordFoundException("id:%d", id));
    }

    /**
     * @param filter
     * @return
     */
    @Override
    public List<Appointment> getByFilter(Filter filter) {
        return appointmentRepository.findAll();
    }

    /**
     * @param filter
     * @param pageable
     * @return
     */
    @Override
    public Page<Appointment> getByFilter(Filter filter, Pageable pageable) {
        return appointmentRepository.findAll(pageable);
    }

    /**
     * @param appointment
     * @return
     */
    @Override
    public Appointment update(Appointment appointment) {
        return null;
    }

    /**
     * @param appointments
     * @return
     */
    @Override
    public List<Appointment> update(List<Appointment> appointments) {
        return null;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Appointment delete(Long id) {
        return null;
    }


    /**
     * Filters the appointments for the users.
     *
     * @param appointments
     * @return
     */
    @PreFilter("principal.id == filterObject.user.id")
    public String saveAll(List<Appointment> appointments) {
        return appointments.stream().map(appointment -> appointment.getUser().getEmail())
            .collect(Collectors.joining(" "));
//		final StringBuilder userAppointments = new StringBuilder();
//
//		//
//		if (!Objects.isNull(appointments)) {
//			appointments.forEach(appointment -> {
//				if (userAppointments.length() > 0) {
//					userAppointments.append(" ");
//				}
//				userAppointments.append(appointment.getUser().getEmail());
//			});
//		}
//
//		return userAppointments.toString();
    }

    /**
     * @param autoUser
     * @return
     */
    public static Appointment createAppointment(final AutoUser autoUser) {
        final Appointment appointment = new Appointment();
        appointment.setUser(autoUser);
        return appointment;
    }
}
