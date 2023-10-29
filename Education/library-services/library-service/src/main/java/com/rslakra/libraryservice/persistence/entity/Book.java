package com.rslakra.libraryservice.persistence.entity;

import com.devamatre.framework.core.ToString;
import com.devamatre.framework.spring.persistence.entity.NamedEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 3:56 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
//@EntityListeners(BaseEntityListener.class)
@Table(name = "books")
public class Book extends NamedEntity<Long> {

    @Column(name = "title", length = 64, nullable = false)
    private String title;

    @Column(name = "published_on", nullable = false, updatable = false)
    private Long publishedOn;

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Book.class)
            .add("name", getName())
            .toString();
    }

}
