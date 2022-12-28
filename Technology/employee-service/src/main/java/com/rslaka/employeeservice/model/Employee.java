package com.rslaka.employeeservice.model;

public class Employee {

    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.format("Employee <id=%s, name=%s>", getId(), getName());
    }

}
