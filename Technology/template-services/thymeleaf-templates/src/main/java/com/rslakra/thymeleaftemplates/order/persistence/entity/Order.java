package com.rslakra.thymeleaftemplates.order.persistence.entity;

import com.rslakra.thymeleaftemplates.customer.persistence.entity.Customer;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

public class Order {

    private Long id;
    private Calendar date;
    private Customer customer;
    private final Set<OrderLine> orderLines = new LinkedHashSet<OrderLine>();

    public Order() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<OrderLine> getOrderLines() {
        return orderLines;
    }
}
