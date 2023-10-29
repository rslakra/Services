package com.rslakra.healthcare.routinecheckup.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.rslakra.healthcare.routinecheckup.utils.constants.Patterns;
import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.annotation.EndDateLaterBeginning;
import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.group.patient.CreatePatientValidationGroup;
import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.group.patient.UpdatePatientValidationGroup;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:06 PM
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EndDateLaterBeginning(
    dateBeforeFieldName = "diseaseOnsetTime",
    dateAfterFieldName = "endTimeOfIllness",
    groups = {
        UpdatePatientValidationGroup.class,
        CreatePatientValidationGroup.class
    }
)
public class PatientRequestDto {

    @JsonProperty("id")
    @NotNull(groups = UpdatePatientValidationGroup.class)
    @Null(groups = CreatePatientValidationGroup.class)
    @Pattern(regexp = Patterns.UUID_PATTERN)
    private String id;

    @JsonProperty("disease")
    @NotEmpty(groups = {
        CreatePatientValidationGroup.class,
        UpdatePatientValidationGroup.class
    })
    private String disease;

    @JsonProperty("disease_onset_time")
    @NotNull(groups = CreatePatientValidationGroup.class)
    private Date diseaseOnsetTime;

    @JsonProperty("end_time_of_illness")
    private Date endTimeOfIllness;

    @JsonProperty("user_id")
    @NotNull(groups = CreatePatientValidationGroup.class)
    @Null(groups = UpdatePatientValidationGroup.class)
    @Pattern(regexp = Patterns.UUID_PATTERN)
    private String userId;

    @JsonProperty("doctor_id")
    @NotNull(groups = CreatePatientValidationGroup.class)
    @Pattern(regexp = Patterns.UUID_PATTERN)
    private String doctorId;

}
