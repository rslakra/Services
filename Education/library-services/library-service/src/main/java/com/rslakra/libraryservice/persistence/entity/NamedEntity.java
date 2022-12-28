package com.rslakra.libraryservice.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

/**
 * @author Rohtash Lakra
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

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return toString(NamedEntity.class)
            .add("name=" + getName())
            .toString();
    }
}
