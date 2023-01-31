package com.github.karixdev.orderservice.controller;

import com.github.karixdev.orderservice.dto.OrderRequest;
import com.github.karixdev.orderservice.dto.OrderResponse;
import com.github.karixdev.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping
    public ResponseEntity<Page<OrderResponse>> getAll(
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "size", defaultValue = "5") Integer size
    ) {
        return new ResponseEntity<>(
                service.getAll(page, size),
                HttpStatus.OK
        );
    }
}
