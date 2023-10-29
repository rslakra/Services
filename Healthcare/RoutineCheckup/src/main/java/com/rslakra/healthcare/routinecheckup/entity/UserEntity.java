package com.rslakra.healthcare.routinecheckup.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

/**
 * @author Rohtash Lakra
 * @created 8/12/21 4:02 PM
 */
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {

    @Id
    private UUID id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @OneToMany(mappedBy = "userEntity")
    private List<PatientEntity> userPatients;

    @OneToMany(mappedBy = "userEntity")
    private List<DoctorEntity> userDoctors;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private RoleEntity role;

    @Column(name = "mail")
    private String mail;

    @Column(name = "temporary")
    private Boolean isTemporary;

    @Column(name = "creation_time")
    private Date creationTime;

    @Column(name = "profile_pic_url")
    private String profilePicUrl;

    @PrePersist
    public void prePersist() {
        id = UUID.randomUUID();
        creationTime = new Date();
    }

}
