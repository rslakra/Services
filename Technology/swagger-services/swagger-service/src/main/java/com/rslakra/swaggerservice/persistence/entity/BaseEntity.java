package com.rslakra.swaggerservice.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:59 PM
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity<U> extends Auditable<U> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

}
