package com.rslakra.springbootsamples.jwtauthentication.persistence.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NaturalId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "roles")
public class RoleDO extends AbstractDO {

    @NaturalId
    @Enumerated(EnumType.STRING)
    @Column(name = "name", length = 64, unique = true, nullable = false)
    private RoleType name;

}
