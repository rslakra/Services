package com.rslakra.auditingservice.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/4/21 5:59 PM
 */
@MappedSuperclass
public class BaseEntity<U> extends Auditable<U> {

    @Id
    @GeneratedValue
    private Long id;

    public BaseEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
