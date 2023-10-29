package com.rslakra.springservices.thymeleaflayout.task.persistence.entity;

import com.rslakra.springservices.thymeleaflayout.framework.persistence.entity.AbstractEntity;

import java.util.StringJoiner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tasks")
public class Task extends AbstractEntity {

    @Column(length = 128, nullable = false)
    private String name;

    @Column(length = 256)
    private String description;

    @Column(nullable = false)
    private int priority;

    @Column
    private boolean completed;

    public Task() {
    }

    public Task(String name, String description, int priority, boolean completed) {
        this.name = name;
        this.description = description;
        this.priority = priority;
        this.completed = completed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
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
