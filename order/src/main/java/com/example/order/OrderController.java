package com.example.order;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping(path = { "/order" })
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping(path = { "/islive" })
    public ResponseEntity<String> isLive() {
        return ResponseEntity
                .ok("is-live: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
    
    // @GetMapping(path = { "/{id}" })
    // public ResponseEntity<Order> getOrder(@PathVariable(value = "id", required = true) Long id)
    //         throws OrderNotFoundException {

    //     Order order = orderService.getOrder(id).orElseThrow(() -> new OrderNotFoundException());

    //     return ResponseEntity.ok(order);
    // }

    @PostMapping
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {

        return ResponseEntity.ok(this.orderService.create(order));
    }

    @PutMapping(value="/{id}")
    public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order order) {
        
        return ResponseEntity.ok(orderService.update(id, order));
    }

}