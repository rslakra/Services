package com.rslakra.libraryservice.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:59 PM
 */
@Getter
@Setter
@NoArgsConstructor
@EntityListeners(BaseEntityListener.class)
@MappedSuperclass
public class BaseEntity<U> extends Auditable<U> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false, updatable = false)
    private Long id;

    /**
     * @param classType
     * @return
     */
    protected final StringJoiner toString(final Class<?> classType) {
        return new StringJoiner(", ", classType.getSimpleName() + "<", ">")
            .add("id=" + getId())
            ;
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return toString(BaseEntity.class).toString();
    }
}
