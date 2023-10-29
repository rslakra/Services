package com.rslakra.healthcare.routinecheckup.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:01 PM
 */
@Entity
@Table(name = "roles")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoleEntity {

    @Id
    private UUID id;

    @Column(name = "role_name")
    private String roleName;

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID();
    }

}
