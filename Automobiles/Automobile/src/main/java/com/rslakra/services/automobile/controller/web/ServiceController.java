package com.rslakra.services.automobile.controller.web;

import com.devamatre.framework.spring.controller.web.AbstractWebController;
import com.devamatre.framework.spring.filter.Filter;
import com.devamatre.framework.spring.parser.Parser;
import com.rslakra.services.automobile.domain.entities.ServiceType;
import com.rslakra.services.automobile.service.ServiceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra (rohtash.singh@gmail.com)
 * @since 01-03-2020 2:46:04 PM
 */
@Controller
@RequestMapping("/services")
public class ServiceController extends AbstractWebController<ServiceType, Long> {

    private final ServiceTypeService serviceTypeService;

    /**
     * @param serviceTypeService
     */
    @Autowired
    public ServiceController(ServiceTypeService serviceTypeService) {
        this.serviceTypeService = serviceTypeService;
    }

//    /**
//     * @param id
//     */
//    @Override
//    public void validate(Optional id) {
//        super.validate(id);
//    }


    /**
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/{id}")
    public String getServiceById(@PathVariable("id") Long id, Model model) {
        ServiceType serviceType = serviceTypeService.getById(id);
        model.addAttribute("service", serviceType);
        return "services";
    }

    /**
     * @param serviceType
     * @return
     */
    @Override
    public String save(ServiceType serviceType) {
        serviceType = serviceTypeService.create(serviceType);
        return "services";
    }

    /**
     * Displays Services Page with services.
     *
     * @param model
     * @return
     */
    @GetMapping("/")
    @Override
    public String getAll(Model model) {
        List<ServiceType> serviceTypes = serviceTypeService.getAll();
        model.addAttribute("services", serviceTypes);
        return "services";
    }

    /**
     * @param model
     * @param filter
     * @return
     */
    @Override
    public String filter(Model model, Filter filter) {
        return null;
    }

    /**
     * @param model
     * @param idOptional
     * @return
     */
    @Override
    public String editObject(Model model, Long idOptional) {
        return null;
    }

    /**
     * @param model
     * @param idOptional
     * @return
     */
    @Override
    public String delete(Model model, Long idOptional) {
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

    /**
     * @return
     */
    @Override
    public Parser<ServiceType> getParser() {
        return null;
    }
}
