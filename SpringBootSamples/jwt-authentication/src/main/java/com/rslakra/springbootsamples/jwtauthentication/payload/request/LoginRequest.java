package com.rslakra.springbootsamples.jwtauthentication.payload.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {

    @NotBlank
    @Size(min = 3, max = 60)
    private String userName;

    @NotBlank
    @Size(min = 6, max = 40)
    private String password;

}
