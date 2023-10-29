package com.rslakra.libraryservice.persistence.entity;

import com.devamatre.framework.core.ToString;
import com.devamatre.framework.spring.persistence.entity.AbstractEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra
 * @created 8/4/21 6:03 PM
 */
@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "file_history")
public class FileHistory extends AbstractEntity<Long> {

    private String contents;

    @Enumerated(EnumType.STRING)
    private Operation operation;

    @ManyToOne
    @JoinColumn(name = "file_id", foreignKey = @ForeignKey(name = "FK_FileHistory_File"))
    private File file;

    /**
     * @param file
     * @param operation
     */
    public FileHistory(File file, Operation operation) {
        if (file != null) {
            this.file = file;
            this.contents = file.getContents();
        }

        this.operation = operation;
    }

    /**
     * Returns the string representation of this object.
     *
     * @return
     */
    @Override
    public String toString() {
        return ToString.of(FileHistory.class)
            .add("contents=" + getContents())
            .add("operation=" + getOperation())
            .add("file=" + getFile())
            .toString();
    }
}
