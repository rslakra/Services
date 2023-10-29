package com.rslakra.thymeleaf.persistence.repository;

import com.rslakra.thymeleaf.persistence.entities.Order;
import com.rslakra.thymeleaf.persistence.manager.OrderManager;

import java.util.ArrayList;
import java.util.List;

public class OrderRepository {

    private static final OrderRepository INSTANCE = new OrderRepository();

    private OrderRepository() {
        super();
    }

    public static OrderRepository getInstance() {
        return INSTANCE;
    }

    public List<Order> findAll() {
        return new ArrayList<Order>(OrderManager.INSTANCE.findAll());
    }

    public Order findById(final Long id) {
        return OrderManager.INSTANCE.findById(id);
    }

    public List<Order> findByCustomerId(final Long customerId) {
        return OrderManager.INSTANCE.findByCustomerId(customerId);
    }

}
