package com.rslakra.healthcare.routinecheckup.keyvalue.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;
import org.springframework.security.web.csrf.CsrfToken;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:11 PM
 */
@KeySpace("csrf_token")
@Getter
@AllArgsConstructor
public class UserCsrfToken {

    @Id
    private String jwt;

    private CsrfToken token;

}
