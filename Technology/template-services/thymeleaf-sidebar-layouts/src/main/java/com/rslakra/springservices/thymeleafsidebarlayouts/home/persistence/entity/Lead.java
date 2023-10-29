package com.rslakra.springservices.thymeleafsidebarlayouts.home.persistence.entity;

import com.rslakra.springservices.thymeleafsidebarlayouts.framework.persistence.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra
 * @created 2/9/23 3:51 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "leads")
public class Lead extends AbstractEntity {

    private String email;
    private String firstName;
    private String lastName;
    private String country;
    private String message;

}
