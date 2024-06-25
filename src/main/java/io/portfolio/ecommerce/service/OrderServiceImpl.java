package io.portfolio.ecommerce.service;

import io.portfolio.ecommerce.model.Order;
import io.portfolio.ecommerce.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service // indicates that this class is a service
@Transactional // indicates that all database operations should be wrapped in a transaction
public class OrderServiceImpl implements OrderService {

    private  OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Iterable<Order> getAllOrders() {
        return this.orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {

        //set the order date
        order.setDateCreated(LocalDate.now());
        return this.orderRepository.save(order);
    }


    @Override
    public void updateOrder(Order order) {
        this.orderRepository.save(order);
    }

}

