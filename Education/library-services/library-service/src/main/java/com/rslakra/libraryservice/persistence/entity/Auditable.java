package com.rslakra.libraryservice.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:40 PM
 */
@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {

    @Column(name = "created_on", nullable = false, updatable = false)
    @CreatedDate
    private Long createdOn;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;

    @Column(name = "created_by", length = 64, nullable = false, updatable = false)
    @CreatedBy
    private U createdBy;

    @Column(name = "updated_on", nullable = false)
    @LastModifiedDate
    private Long updatedOn;

    @Column(name = "updated_at", nullable = false)
    @LastModifiedDate
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;

    @Column(name = "updated_by", length = 64, nullable = false)
    @LastModifiedBy
    private U updatedBy;

    /**
     * @param stringJoiner
     * @return
     */
    protected final StringJoiner addAuditable(final StringJoiner stringJoiner) {
        stringJoiner
            .add("createdOn=" + getCreatedOn())
            .add("createdAt=" + getCreatedAt())
            .add("createdBy=" + getCreatedBy())
            .add("updatedOn=" + getUpdatedOn())
            .add("updatedAt=" + getUpdatedAt())
            .add("updatedBy=" + getUpdatedBy());
        return stringJoiner;
    }

}
