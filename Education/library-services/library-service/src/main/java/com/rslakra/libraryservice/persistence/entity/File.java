package com.rslakra.libraryservice.persistence.entity;

import com.devamatre.framework.core.ToString;
import com.devamatre.framework.spring.persistence.entity.NamedEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 5:59 PM
 */
@Getter
@Setter
@Entity
@EntityListeners(FileEntityListener.class)
@Table(name = "files")
public class File extends NamedEntity<Long> {

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
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(File.class)
            .add("name", getName())
            .add("contents", getContents())
            .toString();
    }
}
