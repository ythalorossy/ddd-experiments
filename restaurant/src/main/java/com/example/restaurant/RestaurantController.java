package com.example.restaurant;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.example.order.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping(path = {"/restaurant"})
public class RestaurantController {

    @Autowired RestaurantService restaurantService;

    @GetMapping(path = { "/islive" })
    public ResponseEntity<String> isLive() {
        return ResponseEntity
                .ok("is-live: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }

    @PostMapping(value="/cook")
    public ResponseEntity<Order> cook(@RequestBody Order order) {
        
        return ResponseEntity.ok(restaurantService.cook(order));
    }
    

}