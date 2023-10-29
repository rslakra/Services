package com.rslakra.healthcare.routinecheckup.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:09 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DoctorResponseDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("speciality")
    private String speciality;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

}
