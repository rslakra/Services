package com.rslakra.thymeleaftemplates.order.persistence.entity;

import com.rslakra.thymeleaftemplates.product.persistence.entity.Product;

import java.math.BigDecimal;

public class OrderLine {

    private Product product;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal amount;

    public OrderLine() {
        super();
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void calculateAmount() {
        setAmount(BigDecimal.valueOf(getQuantity()).multiply(getPrice()));
    }
}
