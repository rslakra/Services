package com.rslakra.thymeleaf.persistence.entities;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private Long id;
    private String name;
    private BigDecimal price;
    private boolean inStock;
    private List<Comment> comments = new ArrayList<Comment>();

    public Product() {
        super();
    }

    public Product(final Long id, final String name, final boolean inStock, final BigDecimal price) {
        super();
        this.id = id;
        this.name = name;
        this.price = price;
        this.inStock = inStock;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
