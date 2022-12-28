package com.rslakra.auditingservice.persistence.entity;

import java.util.StringJoiner;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:59 PM
 */
@Entity
@EntityListeners(FileEntityListener.class)
//@EntityListeners(AuditingEntityListener.class)
@Table(name = "files")
public class File extends BaseEntity<String> {

    private String name;
    private String contents;

    public File() {
        super();
    }

    public File(String name, String contents) {
        this.name = name;
        this.contents = contents;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
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
