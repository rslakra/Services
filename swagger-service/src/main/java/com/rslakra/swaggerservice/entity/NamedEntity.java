package com.rslakra.swaggerservice.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/20/21 7:14 PM
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
