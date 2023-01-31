package com.github.karixdev.orderservice.service;

import com.github.karixdev.orderservice.dto.OrderRequest;
import com.github.karixdev.orderservice.dto.OrderResponse;
import com.github.karixdev.orderservice.entity.Order;
import com.github.karixdev.orderservice.mapper.OrderDtoMapper;
import com.github.karixdev.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;
    private final OrderDtoMapper mapper;

    @Transactional
    public OrderResponse create(OrderRequest payload) {
        Order order = repository.save(Order.builder()
                .email(payload.email())
                .productId(payload.productId())
                .build());

        return mapper.map(order);
    }
}

