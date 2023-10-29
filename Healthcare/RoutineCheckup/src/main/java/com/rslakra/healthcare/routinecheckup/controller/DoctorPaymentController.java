package com.rslakra.healthcare.routinecheckup.controller;

import com.rslakra.healthcare.routinecheckup.dto.response.DoctorPaymentDto;
import com.rslakra.healthcare.routinecheckup.service.FinancialReportService;
import com.rslakra.healthcare.routinecheckup.utils.constants.Patterns;
import com.rslakra.healthcare.routinecheckup.utils.constants.ViewNames;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

import javax.validation.constraints.Pattern;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 3:58 PM
 */
@RestController
@RequiredArgsConstructor
public class DoctorPaymentController {

    private final FinancialReportService financialReportService;

    @GetMapping(ViewNames.DOCTOR_PAYMENT_URL_TEMPLATE)
    public DoctorPaymentDto getDoctorPayment(
        @PathVariable("doc_id")
        @Pattern(regexp = Patterns.UUID_PATTERN)
            String docId,
        Principal principal
    ) {
        String login = principal.getName();
        DoctorPaymentDto result
            = financialReportService
            .getCurrentMonthPayment(login, docId);

        return result;
    }

}
