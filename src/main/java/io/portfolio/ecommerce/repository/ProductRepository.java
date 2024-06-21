package io.portfolio.ecommerce.repository;
import io.portfolio.ecommerce.model.Product;
import org.springframework.data.repository.CrudRepository;
import io.portfolio.ecommerce.exception.ResourceNotFoundException;
/**
 * This interface, ProductRepository,
 * extends the CrudRepository interface provided by Spring Data.
 * By extending CrudRepository<Product, Long>,
 * ProductRepository inherits CRUD (Create, Read, Update, Delete) operations
 * for entities of type Product with an identifier of type Long.
 * The CrudRepository interface provides
 * generic CRUD operations that can be used for data manipulation in a repository. */
public interface ProductRepository extends CrudRepository<Product, Long> {
}
