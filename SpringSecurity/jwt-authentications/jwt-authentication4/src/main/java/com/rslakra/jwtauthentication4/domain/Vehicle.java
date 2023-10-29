package com.rslakra.jwtauthentication4.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "vehicles")
public class Vehicle extends AbstractAuditableEntity<User, Long> implements Serializable {

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Brand brand = Brand.FORD;

}
