package com.rslakra.thymeleaf.service;

import com.rslakra.thymeleaf.persistence.entities.Customer;
import com.rslakra.thymeleaf.persistence.repository.CustomerRepository;

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
