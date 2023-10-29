package com.rslakra.thymeleaftemplates.product.persistence.manager;

import com.rslakra.thymeleaftemplates.order.persistence.entity.Comment;
import com.rslakra.thymeleaftemplates.product.persistence.entity.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Rohtash Lakra
 * @created 6/4/20 4:08 PM
 */
public enum ProductManager {

    INSTANCE;

    private final Map<Long, Product> productsById;

    private ProductManager() {
        this.productsById = new LinkedHashMap<Long, Product>();
        initProducts();
    }

    /**
     * @param id
     * @return
     */
    private Product getProduct(Long id) {
        return productsById.get(id);
    }

    /**
     * @param product
     */
    private void addProduct(Product product) {
        this.productsById.put(product.getId(), product);
    }

    private void initProducts() {
        addProduct(new Product(1L, "Fresh Sweet Basil", true, new BigDecimal("4.99")));
        addProduct(new Product(2L, "Italian Tomato", false, new BigDecimal("1.25")));
        addProduct(new Product(3L, "Yellow Bell Pepper", true, new BigDecimal("2.50")));
        addProduct(new Product(4L, "Old Cheddar", true, new BigDecimal("18.75")));
        addProduct(new Product(5L, "Extra Virgin Coconut Oil", true, new BigDecimal("6.34")));
        addProduct(new Product(6L, "Organic Tomato Ketchup", true, new BigDecimal("1.99")));
        addProduct(new Product(7L, "Whole Grain Oatmeal Cereal", true, new BigDecimal("3.07")));
        addProduct(new Product(8L, "Traditional Tomato & Basil Sauce", true, new BigDecimal("2.58")));
        addProduct(new Product(9L, "Quinoa Flour", true, new BigDecimal("3.02")));
        addProduct(new Product(10L, "Grapefruit Juice", true, new BigDecimal("2.58")));
        addProduct(new Product(11L, "100% Pure Maple Syrup", true, new BigDecimal("5.98")));
        addProduct(new Product(12L, "Marinara Pasta Sauce", false, new BigDecimal("2.08")));
        addProduct(new Product(13L, "Vanilla Puff Cereal", false, new BigDecimal("1.75")));
        addProduct(new Product(14L, "Extra Virgin Oil", false, new BigDecimal("5.01")));
        addProduct(new Product(15L, "Roasted Garlic Pasta Sauce", true, new BigDecimal("2.40")));
        addProduct(new Product(16L, "Canned Minestrone Soup", true, new BigDecimal("2.19")));
        addProduct(new Product(17L, "Almond Milk 1L", true, new BigDecimal("3.24")));
        addProduct(new Product(18L, "Organic Chicken & Wild Rice Soup", true, new BigDecimal("3.17")));
        addProduct(new Product(19L, "Purple Carrot, Blackberry, Quinoa & Greek Yogurt", true, new BigDecimal("8.88")));
        addProduct(new Product(20L, "Pumpkin, Carrot and Apple Juice", false, new BigDecimal("3.90")));
        addProduct(new Product(21L, "Organic Canola Oil", true, new BigDecimal("10.13")));
        addProduct(new Product(22L, "Potato Corn Tortilla Chips", true, new BigDecimal("2.44")));
        addProduct(new Product(23L, "Canned Corn Chowder Soup", true, new BigDecimal("2.30")));
        addProduct(new Product(24L, "Organic Lemonade Juice", true, new BigDecimal("2.48")));
        addProduct(new Product(25L, "Spicy Basil Dressing", true, new BigDecimal("4.72")));
        addProduct(new Product(26L, "Sweet Agave Nectar", true, new BigDecimal("6.46")));
        addProduct(new Product(27L, "Dark Roasted Peanut Butter", false, new BigDecimal("3.48")));
        addProduct(new Product(28L, "Unsweetened Lemon Green Tea", true, new BigDecimal("18.34")));
        addProduct(new Product(29L, "Whole Grain Flakes Cereal", true, new BigDecimal("3.52")));
        addProduct(new Product(30L, "Berry Chewy Granola Bars", true, new BigDecimal("4.00")));

        Product product = getProduct(2L);
        product.getComments().add(new Comment(1, "I'm so sad this product is no longer available!"));
        product.getComments().add(new Comment(2, "When do you expect to have it back?"));

        product = getProduct(13L);
        product.getComments().add(new Comment(3, "Very tasty! I'd definitely buy it again!"));
        product.getComments().add(new Comment(4, "My kids love it!"));
        product.getComments()
            .add(new Comment(5, "Good, my basic breakfast cereal. Though maybe a bit in the sweet side..."));
        product.getComments()
            .add(new Comment(6, "Not that I find it bad, but I think the vanilla flavouring is too intrusive"));
        product.getComments()
            .add(new Comment(7, "I agree with the excessive flavouring, but still one of my favourites!"));
        product.getComments().add(new Comment(8, "Cheaper than at the local store!"));
        product.getComments().add(new Comment(9, "I'm sorry to disagree, but IMO these are far too sweet"));
        product.getComments().add(new Comment(10, "Good. Pricey though."));

        product = getProduct(9L);
        product.getComments().add(new Comment(11, "Made bread with this and it was great!"));
        product.getComments().add(new Comment(12, "Note: this comes actually mixed with wheat flour"));

        product = getProduct(14L);
        product.getComments().add(new Comment(13, "Awesome Spanish oil. Buy it now."));
        product.getComments().add(new Comment(14, "Would definitely buy it again. Best one I've tasted"));
        product.getComments().add(new Comment(15, "A bit acid for my taste, but still a very nice one."));
        product.getComments().add(new Comment(16, "Definitely not the average olive oil. Really good."));

        product = getProduct(16L);
        product.getComments().add(new Comment(17, "Great value!"));

        product = getProduct(24L);
        product.getComments().add(new Comment(18, "My favourite :)"));

        product = getProduct(30L);
        product.getComments().add(new Comment(19, "Too hard! I would not buy again"));
        product.getComments()
            .add(new Comment(20, "Taste is OK, but I agree with previous comment that bars are too hard to eat"));
        product.getComments().add(new Comment(21, "Would definitely NOT buy again. Simply unedible!"));
    }

    public List<Product> findAll() {
        return new ArrayList<Product>(productsById.values());
    }

    public Product findById(final Long id) {
        return productsById.get(id);
    }

}
