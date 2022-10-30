package com.rslakra.liquibaseservice.persistence.entity.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * @author Rohtash Lakra
 * @created 10/20/22 4:01 PM
 */
@Getter
@Setter
@Entity(name = "users")
public class User extends NamedEntity<String> {

}
