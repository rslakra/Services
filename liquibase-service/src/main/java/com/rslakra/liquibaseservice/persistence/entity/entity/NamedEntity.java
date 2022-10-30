package com.rslakra.liquibaseservice.persistence.entity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;

/**
 * @author Rohtash Lakra
 * @created 10/24/22 3:01 PM
 */
@Getter
@Setter
@MappedSuperclass
public class NamedEntity<U> extends BaseEntity<U> {

    private String name;

}
