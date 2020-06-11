package com.example.restaurant;

import java.util.Random;

import com.example.order.Order;
import com.example.order.Order.Status;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestaurantService {

	@Autowired private RabbitTemplate rabbitTemplate;

	public Order cook(Order order) {
		
		try {

			order.setStatus(Status.COOKING);
			
			// Simulates cooking...
			Thread.sleep(new Random().nextInt(15000));
			
			order.setStatus(Status.COOKED);
			rabbitTemplate.convertAndSend("spring-bootexchange", "foo.bar.baz", order);

		} catch (Exception e) {
			
		}

		return order;
	}

}
