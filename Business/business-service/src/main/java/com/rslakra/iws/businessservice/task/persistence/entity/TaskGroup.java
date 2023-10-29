package com.rslakra.iws.businessservice.task.persistence.entity;

import com.rslakra.frameworks.spring.persistence.entity.AbstractEntity;
import com.rslakra.frameworks.spring.persistence.entity.NamedEntity;
import com.rslakra.iws.businessservice.account.persistence.entity.Role;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author Rohtash Lakra
 * @created 7/20/23 12:17 PM
 */
@Getter
@Setter
@NoArgsConstructor
//@Entity
//@Table(name = "task_groups")
public class TaskGroup extends NamedEntity {

    @Transient
    private List<Task> tasks;
}
