package com.rslakra.iws.businessservice.project.persistence.entity;

import com.rslakra.frameworks.spring.persistence.entity.NamedEntity;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 7/24/23 12:27 PM
 */
@Getter
@Setter
public class Project extends NamedEntity {

    List<Feature> features;

}
