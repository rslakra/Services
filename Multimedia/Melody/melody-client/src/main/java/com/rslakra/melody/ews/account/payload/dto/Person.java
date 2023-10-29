package com.rslakra.melody.ews.account.payload.dto;

import com.devamatre.framework.core.ToString;
import com.devamatre.framework.spring.payload.dto.AbstractEntityDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * @author Rohtash Lakra
 * @created 10/9/21 3:56 PM
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
public class Person extends AbstractEntityDTO<Long> {

    private String email;
    private String firstName;
    private String middleName;
    private String lastName;

    @Transient
    public String getName() {
        return (getFirstName() + " " + getLastName());
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(Person.class)
            .add("email", getEmail())
            .add("firstName", getFirstName())
            .add("middleName", getMiddleName())
            .add("lastName", getLastName())
            .toString();
    }

}
