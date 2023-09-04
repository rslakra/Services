package com.rslakra.jwtauthentication4.domain;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

@Setter
@Getter
@MappedSuperclass
public abstract class AbstractPersistableEntity<ID extends Serializable> implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private ID id;

    @Version
    private Long version;
}
