package com.rslakra.healthcare.routinecheckup.service;


import com.rslakra.healthcare.routinecheckup.dto.response.DoctorPaymentDto;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:14 PM
 */
public interface FinancialReportService {

    DoctorPaymentDto getCurrentMonthPayment(String userLogin, String doctorId);

}
