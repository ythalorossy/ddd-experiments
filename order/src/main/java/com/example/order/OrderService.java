package com.example.order;

public interface OrderService {
    
    Order createOrder(String[] plates, String[] drinks, OrderType orderType );
}