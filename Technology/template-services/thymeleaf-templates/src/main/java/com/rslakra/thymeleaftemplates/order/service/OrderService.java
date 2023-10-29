package com.rslakra.thymeleaftemplates.order.service;

import com.rslakra.thymeleaftemplates.order.persistence.entity.Order;
import com.rslakra.thymeleaftemplates.order.persistence.repository.OrderRepository;

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
