package com.rslakra.thymeleaf.persistence.entities;

import java.util.Date;

/**
 * @author Rohtash Lakra
 * @created 6/4/20 4:00 PM
 */
public class UserDetail {

    private String nationality;
    private Date dateOfBirth;
    private Integer age;

    public UserDetail() {
        super();
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
