package com.rslakra.thymeleaf.persistence.entities;

import java.util.Calendar;

public class Customer {

    private Long id;
    private String name;
    private Calendar customerSince;

    public Customer() {
        super();
    }

    public Customer(Long id, String name, Calendar customerSince) {
        super();
        this.id = id;
        this.name = name;
        this.customerSince = customerSince;
    }

    public Customer(Long id, String name) {
        this(id, name, Calendar.getInstance());
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

    public Calendar getCustomerSince() {
        return customerSince;
    }

    public void setCustomerSince(Calendar customerSince) {
        this.customerSince = customerSince;
    }
}
