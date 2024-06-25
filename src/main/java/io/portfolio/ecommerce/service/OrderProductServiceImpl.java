package io.portfolio.ecommerce.service;

import io.portfolio.ecommerce.model.OrderProduct;
import io.portfolio.ecommerce.repository.OrderProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service // marks class as a service to be injected meaning it will be managed by spring
@Transactional // marks methods as part of a transaction to be managed meaning either all or none of the methods will be executed
//due to the @Transactional annotation which disallows the rollback of the transaction in case of an exception
public class OrderProductServiceImpl implements OrderProductService{

    private OrderProductRepository orderProductRepository;

    public OrderProductServiceImpl(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }
    @Override
    public OrderProduct create(OrderProduct orderProduct) {
        return this.orderProductRepository.save(orderProduct);
    }
}
