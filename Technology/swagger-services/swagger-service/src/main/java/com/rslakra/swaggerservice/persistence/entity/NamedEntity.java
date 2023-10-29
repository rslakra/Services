package com.rslakra.swaggerservice.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Rohtash Lakra
 * @version 1.0.0
 * @since Aug 08, 2021 14:05:20
 */
@Getter
@Setter
@MappedSuperclass
public class NamedEntity extends BaseEntity<String> {

    @Column(name = "name", length = 64, nullable = false)
    private String name;

    public NamedEntity() {
        super();
    }

    /**
     * @param name
     */
    public NamedEntity(String name) {
        this.name = name;
    }
}
