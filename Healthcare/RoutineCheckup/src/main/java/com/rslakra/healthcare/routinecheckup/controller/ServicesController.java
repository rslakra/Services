package com.rslakra.healthcare.routinecheckup.controller;

import com.rslakra.healthcare.routinecheckup.dto.ServiceDataDto;
import com.rslakra.healthcare.routinecheckup.service.ServiceScheduleService;
import com.rslakra.healthcare.routinecheckup.utils.components.holder.Messages;
import com.rslakra.healthcare.routinecheckup.utils.constants.ModelAttributesNames;
import com.rslakra.healthcare.routinecheckup.utils.constants.ViewNames;
import com.rslakra.healthcare.routinecheckup.utils.exceptions.UnknownServiceException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:00 PM
 */
@Controller
@RequiredArgsConstructor
public class ServicesController {

    private final ServiceScheduleService serviceScheduleService;

    private final Messages messages;

    private final List<String> SERVICES = Arrays.asList("on_house", "online");

    @GetMapping(value = ViewNames.SERVICES_URL)
    public String servicesView(
        @RequestParam("act_type") String actionType,
        Model model
    ) {
        validateServiceType(actionType);

        ServiceDataDto serviceDataDto = new ServiceDataDto();
        serviceDataDto.setServiceType(actionType);
        model.addAttribute(ModelAttributesNames.SERVICE_DATA, serviceDataDto);

        return ViewNames.SERVICES_VIEW_NAME;
    }

    @PostMapping(value = ViewNames.SERVICES_URL)
    public String saveService(
        @ModelAttribute(ModelAttributesNames.SERVICE_DATA)
            ServiceDataDto serviceDataDto,
        Principal principal
    ) {
        validateServiceType(serviceDataDto.getServiceType());

        String login = principal.getName();
        serviceScheduleService.saveService(serviceDataDto, login);

        return "redirect:" + ViewNames.DOCTORS_AND_PATIENTS_LIST_URL;
    }

    private void validateServiceType(String type) {
        if (!SERVICES.contains(type)) {
            String message
                = String.format(messages.getUnknownServiceTemplate(), type);
            throw new UnknownServiceException(message);
        }
    }

}
