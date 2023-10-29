package com.rslakra.thymeleaftemplates.customer.persistence.repository;

import com.rslakra.thymeleaftemplates.customer.persistence.entity.Customer;
import com.rslakra.thymeleaftemplates.customer.persistence.manager.CustomerManager;

import java.util.ArrayList;
import java.util.List;


public class CustomerRepository {

    private static final CustomerRepository INSTANCE = new CustomerRepository();

    private CustomerRepository() {
        super();
    }

    public static CustomerRepository getInstance() {
        return INSTANCE;
    }

    public List<Customer> findAll() {
        return new ArrayList<Customer>(CustomerManager.INSTANCE.findAll());
    }

    public Customer findById(final Long id) {
        return CustomerManager.INSTANCE.findById(id);
    }

}
