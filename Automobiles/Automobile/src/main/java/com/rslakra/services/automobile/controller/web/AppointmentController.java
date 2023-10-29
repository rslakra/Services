package com.rslakra.services.automobile.controller.web;

import com.devamatre.framework.core.enums.RoleType;
import com.devamatre.framework.spring.controller.web.AbstractWebController;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.parser.Parser;
import com.rslakra.services.automobile.domain.entities.Appointment;
import com.rslakra.services.automobile.domain.entities.AuthUtils;
import com.rslakra.services.automobile.service.AppointmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

import javax.annotation.security.RolesAllowed;

@Controller
@RequestMapping("/appointments")
public class AppointmentController extends AbstractWebController<Appointment, Long> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AppointmentController.class);

    private final AppointmentService appointmentService;

    /**
     * @param appointmentService
     */
    @Autowired
    public AppointmentController(AppointmentService appointmentService) {
        LOGGER.debug("AppointmentController({})", appointmentService);
        this.appointmentService = appointmentService;
    }

    /**
     * @return
     */
    @GetMapping("/all")
    @PostFilter("principal.id == filterObject.user.id")
    public List<Appointment> getAppointments() {
        LOGGER.debug("getAppointments()");
        return appointmentService.getAll();
    }


    /**
     * @param auth
     * @return
     */
    @ModelAttribute("isUser")
    public boolean isUser(Authentication auth) {
        LOGGER.debug("isUser({})", auth);
        return (auth != null && auth.getAuthorities().contains(AuthUtils.getAuthority(RoleType.USER)));
    }

    /**
     * Tests the pre-filter annotation.
     *
     * @param auth
     * @return
     */
//    @GetMapping("/pre-auth-appointments")
//    public String createAppointments(Authentication auth) {
//        AutoUser user = (AutoUser) auth.getPrincipal();
//        // create a new user
//        AutoUser newUser = new AutoUser();
//        newUser.setEmail("work.lakra@gmail.com");
//        newUser.setId(1001L);
//
//        appointmentService.cre
//
//        return utils.saveAll(new ArrayList<Appointment>() {
//            {
//                add(Utils.createAppointment(user));
//                add(Utils.createAppointment(newUser));
//            }
//        });
//    }

    /**
     * @return
     */
    @ModelAttribute
    public Appointment getAppointment() {
        LOGGER.debug("getAppointment()");
        return new Appointment();
    }

    /**
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
//	@PostAuthorize("returnObject == 'appointment'")
    @PostAuthorize("hasPermission(#model['appointment'],'read')")
    public String getAppointment(@PathVariable("id") Long id, Model model) {
        LOGGER.debug("+getAppointment({})", id, model);
        Appointment appointment = appointmentService.getById(id);
        model.addAttribute("appointment", appointment);
        LOGGER.debug("-getAppointment(), model: {}", model);
        return "appointment";
    }

    /**
     * @return
     */
    @GetMapping("/confirm")
    @RolesAllowed("ROLE_ADMIN")
    public String confirm() {
        LOGGER.debug("confirm()");
        return "confirmed";
    }

    /**
     * @return
     */
    @GetMapping("/confirm/{id}")
//	@RolesAllowed("ROLE_ADMIN")
    @PostAuthorize("hasPermission(returnObject,'administration')")
    public Appointment confirm(@PathVariable("id") Long id) {
        LOGGER.debug("confirm({})", id);
        return appointmentService.getById(id);
    }

    /**
     * @return
     */
    @GetMapping("/cancel")
    public String cancel() {
        LOGGER.debug("cancel()");
        return "cancelled";
    }

    /**
     * @return
     */
    @GetMapping("/complete")
    @RolesAllowed("ROLE_ADMIN")
    public String complete() {
        LOGGER.debug("complete()");
        return "completed";
    }

    /**
     * @param appointment
     * @return
     */
    @PostMapping(value = "/save")
    @Override
    public String save(@ModelAttribute Appointment appointment) {
        LOGGER.debug("+save({})", appointment);
        appointment = appointmentService.create(appointment);
        LOGGER.debug("-save(), appointment: {}", appointment);
        return "redirect:/";
    }

    /**
     * @param model
     * @return
     */
    @GetMapping("/")
    @Override
    public String getAll(Model model) {
        LOGGER.debug("+getAll({})", model);
        List<Appointment> appointments = appointmentService.getAll();
        model.addAttribute("appointments", appointments);
        LOGGER.debug("-getAll(), model:{}", model);
        return "appointments";
    }

    /**
     * @param model
     * @param filter
     * @return
     */
    @Override
    public String filter(Model model, Filter filter) {
        LOGGER.debug("filter({}, {})", model, filter);
        return null;
    }

    /**
     * @param model
     * @param idOptional
     * @return
     */
    @Override
    public String editObject(Model model, Long idOptional) {
        LOGGER.debug("editObject({}, {})", model, idOptional);
        return null;
    }

    /**
     * @param model
     * @param idOptional
     * @return
     */
    @Override
    public String delete(Model model, Long idOptional) {
        LOGGER.debug("delete({}, {})", model, idOptional);
        return null;
    }

    /**
     * @return
     */
    @Override
    public Parser<Appointment> getParser() {
        return null;
    }

    /**
     * @param model
     * @param allParams
     * @return
     */
    @Override
    public String filter(Model model, Map<String, Object> allParams) {
        return null;
    }
}
