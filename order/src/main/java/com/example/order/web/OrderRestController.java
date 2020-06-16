package com.example.order.web;

import javax.validation.Valid;

import com.example.order.Order;
import com.example.order.OrderServiceImpl;
import com.example.order.OrderType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { "/api/orders" })
public class OrderRestController {

    private final OrderServiceImpl orderService;

    @Autowired
    public OrderRestController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public OrderDto createOrder(@Valid @RequestBody CreateOrderParameters parameters) {

        Order order = this.orderService.createOrder(
                            parameters.getPlates(), 
                            parameters.getDrinks(),
                            OrderType.valueOf(parameters.getOrderType()));

        return OrderDto.fromOrder(order);
    }

}