package com.example.order;

import java.time.LocalDate;
import java.util.Optional;

import com.example.order.Order.Status;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public Order create(Order order) {

        order.setCreatedAt(LocalDate.now());
        order.setUpdatedAt(LocalDate.now());
        order.setStatus(Status.CREATED);

        orderRepository.save(order);

        rabbitTemplate.convertAndSend("spring-bootexchange", "foo.bar.baz", order);

        return order;
    }

    public Optional<Order> getOrder(Long id) {
        return orderRepository.findById(id);
    }

	public Order update(Long id, Order order) {

        return orderRepository
            .findById(id)
            .map(entity -> {
                entity.setDescription(order.getDescription());
                entity.setStatus(order.getStatus());
                entity.setUpdatedAt(LocalDate.now());
                return orderRepository.save(entity);
            })
            .orElseGet(() -> {
                order.setCreatedAt(LocalDate.now());
                order.setUpdatedAt(LocalDate.now());
                return orderRepository.save(order);
            });
	}
}