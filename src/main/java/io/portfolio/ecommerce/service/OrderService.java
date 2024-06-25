package io.portfolio.ecommerce.service;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;
import io.portfolio.ecommerce.model.Order;
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
  */
@Validated
public interface OrderService {

    @NotNull Iterable<Order> getAllOrders();

    //@Valid annotation indicates validation should be performed on method parameters
    Order createOrder(@NotNull(message = "order cannot be null") @Valid Order order);
    void updateOrder(@NotNull(message = "order cannot be null") @Valid Order order);
}
