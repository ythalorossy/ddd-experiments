package com.example.payment;

public class OrderNotPaidException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public OrderNotPaidException() {
    }

    public OrderNotPaidException(Long id) {
        super("Order not paid: " + id);
    }
}