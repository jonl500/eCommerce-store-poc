package io.portfolio.ecommerce.service;
import org.springframework.validation.annotation.Validated;
import io.portfolio.ecommerce.model.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

/**
    @Validated annotation indicates validation should be performed on method parameters
    Declaration of ProductService interface
    @NotNull annotation ensures returned iterable is not null
    Method to get all products
    @Min annotation ensures id is at least 1
    Method to get a product by id
    Method to save a product
**/
@Validated
public interface ProductService {
    @NotNull Iterable<Product> getAllProducts();

    Product getProduct(@Min(value = 1L, message = "Invalid product ID.") long id);

    Product save(Product product);
}
