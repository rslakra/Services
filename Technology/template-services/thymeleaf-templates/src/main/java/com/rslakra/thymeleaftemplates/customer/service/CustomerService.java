package com.rslakra.thymeleaftemplates.customer.service;

import com.rslakra.thymeleaftemplates.customer.persistence.entity.Customer;
import com.rslakra.thymeleaftemplates.customer.persistence.repository.CustomerRepository;

import java.util.List;

public class CustomerService {

    public CustomerService() {
        super();
    }

    public List<Customer> findAll() {
        return CustomerRepository.getInstance().findAll();
    }

    public Customer findById(final Long id) {
        return CustomerRepository.getInstance().findById(id);
    }

}
