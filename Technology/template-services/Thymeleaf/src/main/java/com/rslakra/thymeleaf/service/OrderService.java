package com.rslakra.thymeleaf.service;

import com.rslakra.thymeleaf.persistence.entities.Order;
import com.rslakra.thymeleaf.persistence.repository.OrderRepository;

import java.util.List;

public class OrderService {

    public OrderService() {
        super();
    }

    public List<Order> findAll() {
        return OrderRepository.getInstance().findAll();
    }

    public Order findById(final Long id) {
        return OrderRepository.getInstance().findById(id);
    }

    public List<Order> findByCustomerId(final Long customerId) {
        return OrderRepository.getInstance().findByCustomerId(customerId);
    }

}
