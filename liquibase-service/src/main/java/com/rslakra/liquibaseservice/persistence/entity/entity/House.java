package com.rslakra.liquibaseservice.persistence.entity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * @author Rohtash Lakra
 */
@Getter
@Setter
@Entity(name = "houses")
public class House extends BaseEntity<String> {

    private String owner;
    private boolean fullyPaid;
}
