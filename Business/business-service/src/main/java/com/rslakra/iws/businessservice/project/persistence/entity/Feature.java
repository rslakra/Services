package com.rslakra.iws.businessservice.project.persistence.entity;

import com.rslakra.frameworks.spring.persistence.entity.NamedEntity;
import com.rslakra.iws.businessservice.task.persistence.entity.Task;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author Rohtash Lakra
 * @created 7/24/23 12:04 PM
 */
@Getter
@Setter
@NoArgsConstructor
public class Feature extends NamedEntity {

    List<Task> tasks;

}
