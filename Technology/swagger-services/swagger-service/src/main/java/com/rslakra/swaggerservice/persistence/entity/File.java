package com.rslakra.swaggerservice.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import java.util.StringJoiner;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:59 PM
 */
@Getter
@Setter
@Entity
@EntityListeners(FileEntityListener.class)
//@EntityListeners(AuditingEntityListener.class)
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
