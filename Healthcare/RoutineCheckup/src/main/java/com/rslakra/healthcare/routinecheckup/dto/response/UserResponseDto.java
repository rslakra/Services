package com.rslakra.healthcare.routinecheckup.dto.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:10 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UserResponseDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("login")
    private String login;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("doctors_ids")
    private List<String> doctorsIds;

    @JsonProperty("patients_ids")
    private List<String> patientsIds;

}
