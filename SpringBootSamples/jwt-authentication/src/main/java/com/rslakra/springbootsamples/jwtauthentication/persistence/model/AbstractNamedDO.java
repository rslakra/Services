package com.rslakra.springbootsamples.jwtauthentication.persistence.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author Rohtash Lakra
 * @created 11/29/21 9:24 PM
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class AbstractNamedDO extends AbstractDO {

    @NotBlank
    @Size(min = 3, max = 64)
    @Column(name = "name", length = 64, unique = true, nullable = false)
    private String name;

}
