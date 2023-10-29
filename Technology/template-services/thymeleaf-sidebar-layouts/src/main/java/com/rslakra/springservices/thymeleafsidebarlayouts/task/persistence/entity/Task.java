package com.rslakra.springservices.thymeleafsidebarlayouts.task.persistence.entity;

import com.rslakra.springservices.thymeleafsidebarlayouts.framework.persistence.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tasks")
public class Task extends AbstractEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "priority")
    private int priority;

    @Column(name = "completed")
    private boolean completed;

    /**
     * @param name
     * @param description
     * @param priority
     * @param completed
     */
    public Task(String name, String description, int priority, boolean completed) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return new StringJoiner(",")
            .add("Task <id=" + getId())
            .add(", name=" + getName())
            .add(", description=" + getDescription())
            .add(", priority=" + getPriority())
            .add(", completed=" + isCompleted())
            .add(">")
            .toString();
    }

}
