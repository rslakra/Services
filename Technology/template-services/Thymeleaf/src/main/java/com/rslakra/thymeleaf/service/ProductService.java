package com.rslakra.thymeleaf.service;

import com.rslakra.thymeleaf.persistence.entities.Product;
import com.rslakra.thymeleaf.persistence.repository.ProductRepository;

import java.util.List;

public class ProductService {

    public ProductService() {
        super();
    }

    public List<Product> findAll() {
        return ProductRepository.getInstance().findAll();
    }

    public Product findById(final Long id) {
        return ProductRepository.getInstance().findById(id);
    }

}
