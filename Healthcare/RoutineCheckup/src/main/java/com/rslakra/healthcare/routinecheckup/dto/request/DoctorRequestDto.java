package com.rslakra.healthcare.routinecheckup.dto.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.rslakra.healthcare.routinecheckup.utils.constants.Patterns;
import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.group.doctor.CreateDoctorValidationGroup;
import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.group.doctor.UpdateDoctorValidationGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:06 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class DoctorRequestDto {

    @JsonProperty("id")
    @NotNull(groups = UpdateDoctorValidationGroup.class)
    @Null(groups = CreateDoctorValidationGroup.class)
    @Pattern(regexp = Patterns.UUID_PATTERN)
    private String id;

    @JsonProperty("speciality")
    @NotEmpty(groups = CreateDoctorValidationGroup.class)
    private String speciality;

    @JsonProperty("user_id")
    @NotNull(groups = CreateDoctorValidationGroup.class)
    @Null(groups = UpdateDoctorValidationGroup.class)
    @Pattern(regexp = Patterns.UUID_PATTERN)
    private String userId;

}
