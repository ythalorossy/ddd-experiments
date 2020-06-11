package com.example.gateway;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/fallback")
public class GatewayController {

    @GetMapping("/payment")
	public ResponseEntity<?> routerError() {
        return ResponseEntity
            .status(HttpStatus.EXPECTATION_FAILED)
            .body("Order not created");
	}
}