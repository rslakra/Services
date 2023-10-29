package com.rslakra.healthcare.routinecheckup.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:03 PM
 */
@Entity
@Table(name = "user_files")
@Getter
@Setter
@NoArgsConstructor
public class UserFileEntity {

    @Id
    @Column(name = "file_id")
    private UUID fileId;

    @Column(name = "original_file_name")
    private String originalFileName;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private UserEntity owner;

    @PrePersist
    public void prePersist() {
        fileId = UUID.randomUUID();
    }

}
