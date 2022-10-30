package com.rslakra.swaggerservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra (rlakra)
 * @created 8/4/21 5:59 PM
 */
@Getter
@Setter
@Entity
@EntityListeners(FileEntityListener.class)
@Table(name = "files")
public class File extends NamedEntity {

    private String contents;

    public File() {
        super();
    }

    /**
     * @param name
     * @param contents
     */
    public File(String name, String contents) {
        super(name);
        this.contents = contents;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return new StringJoiner(", ", File.class.getSimpleName() + "[", "]")
            .add("name=" + getName())
            .add("contents=" + getContents())
            .toString();
    }
}
