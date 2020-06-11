package com.example.payment;

import java.util.Random;

import com.example.order.Order;
import com.example.order.Order.Status;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

	public Order pay(Order order) {

        try {
            // Simulates payment...
            Thread.sleep(new Random().nextInt(10000));
            
            order.setStatus(Status.PAID);

            rabbitTemplate.convertAndSend("spring-bootexchange", "foo.bar.baz", order);

        } catch (InterruptedException e) {
            throw new OrderNotPaidException(order.getId());
        }

        return order;
	}
}
