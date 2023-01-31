package com.github.karixdev.orderservice.service;

import com.github.karixdev.orderservice.dto.OrderRequest;
import com.github.karixdev.orderservice.dto.OrderResponse;
import com.github.karixdev.orderservice.entity.Order;
import com.github.karixdev.orderservice.exception.NotFoundException;
import com.github.karixdev.orderservice.mapper.OrderDtoMapper;
import com.github.karixdev.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

    public Page<OrderResponse> getAll(Integer page, Integer size) {
        Pageable pageRequest = PageRequest.of(page, size);

        return repository.findAll(pageRequest).map(mapper::map);
    }

    public OrderResponse getById(UUID id) {
        return mapper.map(getByIdOrThrow(id));
    }

    @Transactional
    public void delete(UUID id) {
        Order order = getByIdOrThrow(id);

        repository.delete(order);
    }

    private Order getByIdOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            "Order with provided id not found");
                });
    }
}

