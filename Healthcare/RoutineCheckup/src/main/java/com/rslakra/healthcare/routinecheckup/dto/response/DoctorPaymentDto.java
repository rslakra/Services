package com.rslakra.healthcare.routinecheckup.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:08 PM
 */
@Data
@AllArgsConstructor
public class DoctorPaymentDto {

    @JsonProperty("doctor_id")
    private String doctorId;

    @JsonProperty("payment")
    private Integer payment;

}
