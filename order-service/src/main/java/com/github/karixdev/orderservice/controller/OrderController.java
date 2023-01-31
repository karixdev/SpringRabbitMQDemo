package com.github.karixdev.orderservice.controller;

import com.github.karixdev.orderservice.dto.OrderRequest;
import com.github.karixdev.orderservice.dto.OrderResponse;
import com.github.karixdev.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService service;

    @PostMapping
    public ResponseEntity<OrderResponse> create(
            @RequestBody OrderRequest payload
    ) {
        return new ResponseEntity<>(
                service.create(payload),
                HttpStatus.CREATED
        );
    }
}
