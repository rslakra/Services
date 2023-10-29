/*
 * =============================================================================
 *
 *   Copyright (c) 2011-2016, The THYMELEAF team (http://www.thymeleaf.org)
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 * =============================================================================
 */
package com.rslakra.thymeleaftemplates.product.persistence.repository;

import com.rslakra.thymeleaftemplates.product.persistence.entity.Product;
import com.rslakra.thymeleaftemplates.product.persistence.manager.ProductManager;

import java.util.ArrayList;
import java.util.List;


public class ProductRepository {

    private static final ProductRepository INSTANCE = new ProductRepository();

    private ProductRepository() {
        super();
    }

    public static ProductRepository getInstance() {
        return INSTANCE;
    }

    public List<Product> findAll() {
        return new ArrayList<Product>(ProductManager.INSTANCE.findAll());
    }

    public Product findById(final Long id) {
        return ProductManager.INSTANCE.findById(id);
    }


}
