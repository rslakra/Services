package com.rslakra.springbootsamples.aopbeforeadvice.model;

public class Advice {

    private Long id;
    private String name;
    private String message;

    /**
     * @param id
     * @param name
     * @param message
     */
    public Advice(Long id, String name, String message) {
        this.id = id;
        this.name = name;
        this.message = message;
    }

    public Advice() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return String.format("Employee <id=%s, name=%s, message=%s>", getId(), getName(), getMessage());
    }
}
