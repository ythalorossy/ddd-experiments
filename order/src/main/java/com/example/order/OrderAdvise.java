package com.example.order;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class OrderAdvise {

    @ResponseBody
    @ExceptionHandler(OrderNotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    String orderNotFoundHandler(OrderNotFoundException orderNotFoundException) {
        return orderNotFoundException.getMessage();
    }
    
}