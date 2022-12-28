package com.rslakra.liquibaseservice.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * @author Rohtash Lakra
 */
@Getter
@Setter
@Entity(name = "items")
public class Item extends NamedEntity<String> {

    @ManyToOne
    private House house;
}
