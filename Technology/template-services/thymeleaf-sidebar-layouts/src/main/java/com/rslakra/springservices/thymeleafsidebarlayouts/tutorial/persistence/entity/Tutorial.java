package com.rslakra.springservices.thymeleafsidebarlayouts.tutorial.persistence.entity;

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
@Table(name = "tutorials")
public class Tutorial extends AbstractEntity {

    @Column(length = 128, nullable = false)
    private String title;

    @Column(length = 256)
    private String description;

    @Column(nullable = false)
    private int level;

    @Column
    private boolean published;

    /**
     * @param title
     * @param description
     * @param level
     * @param published
     */
    public Tutorial(String title, String description, int level, boolean published) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.published = published;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return new StringJoiner(",")
            .add("Tutorial <id=" + getId())
            .add(", title=" + getTitle())
            .add(", description=" + getDescription())
            .add(", level=" + getLevel())
            .add(", published=" + isPublished())
            .add(">")
            .toString();
    }

}
