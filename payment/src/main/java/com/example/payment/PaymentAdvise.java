package com.example.payment;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PaymentAdvise {

    @ResponseBody
    @ExceptionHandler(OrderNotPaidException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    String orderNotPaidHandler(OrderNotPaidException notPaidException) {
        return notPaidException.getMessage();
    }
}