package com.example.order;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, RabbitTemplate rabbitTemplate) {
        this.orderRepository = orderRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Order create(Order order) {

        order.setCreatedAt(LocalDate.now());
        order.setUpdatedAt(LocalDate.now());
        order.setStatus(OrderStatus.CREATED);

        orderRepository.save(order);

        rabbitTemplate.convertAndSend("spring-bootexchange", "foo.bar.baz", order);

        return order;
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

    public Order update(Long id, Order order) {

        return orderRepository.findById(id).map(entity -> {
            entity.setStatus(order.getStatus());
            entity.setUpdatedAt(LocalDate.now());
            return orderRepository.save(entity);
        }).orElseGet(() -> {
            order.setCreatedAt(LocalDate.now());
            order.setUpdatedAt(LocalDate.now());
            return orderRepository.save(order);
        });
    }

    @Override
    public Order createOrder(String[] plates, String[] drinks, OrderType orderType) {

        Order order = Order.createOrder(plates, drinks, orderType);
        
        return orderRepository.save(order);
    }

}