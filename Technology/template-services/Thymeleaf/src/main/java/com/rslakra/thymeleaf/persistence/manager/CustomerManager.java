package com.rslakra.thymeleaf.persistence.manager;

import com.devamatre.framework.core.TimeUtils;
import com.rslakra.thymeleaf.persistence.entities.Customer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 6/4/20 4:08 PM
 */
public enum CustomerManager {

    INSTANCE;

    private final Map<Long, Customer> customersById;

    private CustomerManager() {
        this.customersById = new LinkedHashMap<Long, Customer>();
        initCustomers();
    }

    private void initCustomers() {
        Customer customer = new Customer(1L, "James Cucumber");
        customer.setCustomerSince(TimeUtils.ofDateTime(2006, 4, 2, 13, 20));
        this.customersById.put(customer.getId(), customer);

        customer = new Customer(2L, "Anna Lettuce");
        customer.setCustomerSince(TimeUtils.ofDateTime(2005, 1, 30, 17, 14));
        this.customersById.put(customer.getId(), customer);

        customer = new Customer(3L, "Boris Tomato");
        customer.setCustomerSince(TimeUtils.ofDateTime(2008, 12, 2, 9, 53));
        this.customersById.put(customer.getId(), customer);

        customer = new Customer(4L, "Shannon Parsley");
        customer.setCustomerSince(TimeUtils.ofDateTime(2009, 3, 24, 10, 45));
        this.customersById.put(customer.getId(), customer);

        customer = new Customer(5L, "Susan Cheddar");
        customer.setCustomerSince(TimeUtils.ofDateTime(2007, 10, 1, 15, 02));
        this.customersById.put(customer.getId(), customer);

        customer = new Customer(6L, "George Garlic");
        customer.setCustomerSince(TimeUtils.ofDateTime(2010, 5, 18, 20, 30));
        this.customersById.put(customer.getId(), customer);
    }

    public List<Customer> findAll() {
        return new ArrayList<Customer>(this.customersById.values());
    }

    public Customer findById(final Long id) {
        return this.customersById.get(id);
    }

}
