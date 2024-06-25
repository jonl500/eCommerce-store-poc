package io.portfolio.ecommerce.service;

import io.portfolio.ecommerce.model.OrderProduct;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated // annotation indicates validation should be performed on method parameters
public interface OrderProductService{

    // @Valid annotation indicates validation should be performed on method parameters
    OrderProduct create(@NotNull(message = "orderProduct cannot be null") @Valid OrderProduct orderProduct);
}
