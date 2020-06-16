package com.example.order.web;

import com.example.order.Order;

import lombok.Value;

@Value
public class OrderDto {

    String orderId;
    String orderStatus;
    String orderType;

    public static OrderDto fromOrder(Order order) {
        return new OrderDto(order.getId().toString(), order.getStatus().name(), order.getType().name());
    }

}
