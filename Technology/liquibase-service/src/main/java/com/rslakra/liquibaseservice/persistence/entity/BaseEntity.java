package com.rslakra.liquibaseservice.persistence.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

/**
 * @author Rohtash Lakra
 * @created 10/20/22 12:03 PM
 */
@Getter
@Setter
@EqualsAndHashCode
@MappedSuperclass
public class BaseEntity<U> extends Auditable<U> {

    @Id
    @GeneratedValue
    private Long id;

}
