package com.rslakra.swaggerservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/4/21 5:59 PM
 */
@Getter
@Setter
@MappedSuperclass
public class BaseEntity<U> extends Auditable<U> {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @GeneratedValue
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    public BaseEntity() {
    }

}
