package com.example.payment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.order.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = { "/payment" })
public class PaymentController {

    @Autowired PaymentService paymentService;

    @GetMapping(path = { "islive" })
    public ResponseEntity<String> isLive() {
        return ResponseEntity
                .ok("is-live: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @PostMapping(path = { "/pay" })
    public ResponseEntity<Order> pay(@RequestBody Order order) {

        return ResponseEntity.ok(paymentService.pay(order));
    }

}