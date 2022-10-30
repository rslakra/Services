package com.rslakra.liquibaseservice.persistence.entity.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Rohtash Lakra
 * @created 10/20/22 12:03 PM
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity<U> extends Auditable<U> {

    @Id
    @GeneratedValue
    private Long id;

}
