package com.rslakra.healthcare.routinecheckup.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.rslakra.healthcare.routinecheckup.utils.constants.Patterns;
import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.group.user.CreateUserValidationGroup;
import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.group.user.UpdateUserByAdminValidationGroup;
import com.rslakra.healthcare.routinecheckup.utils.validation.constraint.group.user.UpdateUserValidationGroup;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;


/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:07 PM
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequestDto {

    @JsonProperty("id")
    @NotNull(groups = UpdateUserByAdminValidationGroup.class)
    @Null
    @Pattern(regexp = Patterns.UUID_PATTERN)
    private String id;

    @JsonProperty("login")
    @NotEmpty(groups = CreateUserValidationGroup.class)
    @Null(groups = UpdateUserValidationGroup.class)
    private String login;

    @JsonProperty("pass")
    @NotEmpty(groups = CreateUserValidationGroup.class)
    private String password;

    @JsonProperty("first_name")
    @NotEmpty(groups = CreateUserValidationGroup.class)
    private String firstName;

    @JsonProperty("last_name")
    @NotEmpty(groups = CreateUserValidationGroup.class)
    private String lastName;

    @JsonProperty("mail")
    @NotEmpty(groups = CreateUserValidationGroup.class)
    @Null(groups = UpdateUserValidationGroup.class)
    @Email
    private String mail;

}
