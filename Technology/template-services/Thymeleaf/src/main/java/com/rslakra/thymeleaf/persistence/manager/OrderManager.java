package com.rslakra.thymeleaf.persistence.manager;

import com.rslakra.frameworks.core.TimeUtils;
import com.rslakra.thymeleaf.persistence.entities.Customer;
import com.rslakra.thymeleaf.persistence.entities.Order;
import com.rslakra.thymeleaf.persistence.entities.OrderLine;
import com.rslakra.thymeleaf.persistence.entities.Product;
import com.rslakra.thymeleaf.persistence.repository.ProductRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 6/4/20 4:08 PM
 */
public enum OrderManager {

    INSTANCE;

    private final Map<Long, Order> ordersById;
    private final Map<Long, List<Order>> ordersByCustomerId;

    private OrderManager() {
        this.ordersById = new LinkedHashMap<Long, Order>();
        this.ordersByCustomerId = new LinkedHashMap<Long, List<Order>>();
        initOrders();
    }

    private void initOrders() {

        //1st
        Customer customer = CustomerManager.INSTANCE.findById(1L);
        this.ordersByCustomerId.put(customer.getId(), new ArrayList<Order>());

        //4th
        customer = CustomerManager.INSTANCE.findById(4L);
        this.ordersByCustomerId.put(customer.getId(), new ArrayList<Order>());

        //6th
        customer = CustomerManager.INSTANCE.findById(6L);
        this.ordersByCustomerId.put(customer.getId(), new ArrayList<Order>());

        final Product prod1 = ProductRepository.getInstance().findById(1L);
        final Product prod2 = ProductRepository.getInstance().findById(2L);
        final Product prod3 = ProductRepository.getInstance().findById(3L);
        final Product prod4 = ProductRepository.getInstance().findById(4L);

        final Order order = new Order();
        order.setId(1L);
        Customer customer4 = CustomerManager.INSTANCE.findById(4L);
        order.setCustomer(customer4);
        order.setDate(TimeUtils.ofDateTime(2009, 1, 12, 10, 23));
        this.ordersById.put(order.getId(), order);
        this.ordersByCustomerId.get(customer4.getId()).add(order);

        final OrderLine orderLine = new OrderLine();
        orderLine.setProduct(prod2);
        orderLine.setQuantity(2);
        orderLine.setPrice(new BigDecimal("0.99"));
        orderLine.calculateAmount();
        order.getOrderLines().add(orderLine);

        final OrderLine orderLine12 = new OrderLine();
        orderLine12.setProduct(prod3);
        orderLine12.setQuantity(4);
        orderLine12.setPrice(new BigDecimal("2.50"));
        orderLine12.calculateAmount();
        order.getOrderLines().add(orderLine12);

        final OrderLine orderLine13 = new OrderLine();
        orderLine13.setProduct(prod4);
        orderLine13.setQuantity(1);
        orderLine13.setPrice(new BigDecimal("15.50"));
        orderLine13.calculateAmount();
        order.getOrderLines().add(orderLine13);

        Customer customer6 = CustomerManager.INSTANCE.findById(6L);
        final Order order2 = new Order();
        order2.setId(2L);
        order2.setCustomer(customer6);
        order2.setDate(TimeUtils.ofDateTime(2010, 6, 9, 21, 01));
        this.ordersById.put(order2.getId(), order2);
        this.ordersByCustomerId.get(customer6.getId()).add(order2);

        final OrderLine orderLine21 = new OrderLine();
        orderLine21.setProduct(prod1);
        orderLine21.setQuantity(5);
        orderLine21.setPrice(new BigDecimal("3.75"));
        orderLine21.calculateAmount();
        order2.getOrderLines().add(orderLine21);

        final OrderLine orderLine22 = new OrderLine();
        orderLine22.setProduct(prod4);
        orderLine22.setQuantity(2);
        orderLine22.setPrice(new BigDecimal("17.99"));
        orderLine22.calculateAmount();
        order2.getOrderLines().add(orderLine22);

        final Order order3 = new Order();
        order3.setId(3L);
        order3.setCustomer(CustomerManager.INSTANCE.findById(1L));
        order3.setDate(TimeUtils.ofDateTime(2010, 7, 18, 22, 32));
        this.ordersById.put(order3.getId(), order3);
        this.ordersByCustomerId.get(customer4.getId()).add(order3);

        final OrderLine orderLine32 = new OrderLine();
        orderLine32.setProduct(prod1);
        orderLine32.setQuantity(8);
        orderLine32.setPrice(new BigDecimal("5.99"));
        orderLine32.calculateAmount();
        order3.getOrderLines().add(orderLine32);
    }

    public List<Order> findAll() {
        return new ArrayList<Order>(this.ordersById.values());
    }

    public Order findById(final Long id) {
        return this.ordersById.get(id);
    }

    public List<Order> findByCustomerId(final Long customerId) {
        final List<Order> ordersForCustomerId = this.ordersByCustomerId.get(customerId);
        if (ordersForCustomerId == null) {
            return new ArrayList<Order>();
        }
        return ordersForCustomerId;
    }
}
