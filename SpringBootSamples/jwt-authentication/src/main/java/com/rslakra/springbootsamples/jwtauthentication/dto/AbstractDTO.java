package com.rslakra.springbootsamples.jwtauthentication.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;

/**
 * @author Rohtash Lakra
 * @created 11/29/21 9:18 PM
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractDTO {

//    @NotNull
    private Long id;

}
