package io.portfolio.ecommerce.repository;

import io.portfolio.ecommerce.model.OrderProduct;
import io.portfolio.ecommerce.model.OrderProductPK;
import org.springframework.data.repository.CrudRepository;

public interface OrderProductRepository extends CrudRepository<OrderProduct, OrderProductPK> {
}
