package com.rslakra.thymeleaftemplates.product.service;

import com.rslakra.thymeleaftemplates.product.persistence.entity.Product;
import com.rslakra.thymeleaftemplates.product.persistence.repository.ProductRepository;

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
