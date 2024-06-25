package io.portfolio.ecommerce.dto;

import io.portfolio.ecommerce.model.Product;

/**
 * a DTO class for OrderProduct
 * DTO classes are used to transfer data between the application and the database for the
 * 'Create' and 'Update' operations in CRUD, and for the 'Read' operation in CRUD operations.
 * the DTO class makes it easier to read and write data to the database.
 */

public class OrderProductDto {

    private Product product;
    private int quantity; //TODO: may need to be repalced with Integer class wrapper for null values

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
