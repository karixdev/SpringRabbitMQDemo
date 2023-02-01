package com.github.karixdev.orderservice.service;

import com.github.karixdev.orderservice.config.MQConfig;
import com.github.karixdev.orderservice.dto.EmailRequest;
import com.github.karixdev.orderservice.dto.OrderRequest;
import com.github.karixdev.orderservice.dto.OrderResponse;
import com.github.karixdev.orderservice.dto.OrderUpdateRequest;
import com.github.karixdev.orderservice.entity.Order;
import com.github.karixdev.orderservice.exception.NotFoundException;
import com.github.karixdev.orderservice.mapper.OrderDtoMapper;
import com.github.karixdev.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    private final RabbitTemplate rabbitTemplate;

    @Transactional
    public OrderResponse create(OrderRequest payload) {
        Order order = repository.save(Order.builder()
                .email(payload.email())
                .productId(payload.productId())
                .build());

        EmailRequest emailRequest = EmailRequest.builder()
                .topic("Order placed")
                .recipientEmail(order.getEmail())
                .body(String.format(
                        "Your order has been placed with following product: %s",
                        order.getId().toString()))
                .build();

        sendEmailMessage(emailRequest);

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

    @Transactional
    public OrderResponse update(UUID id, OrderUpdateRequest payload) {
        Order order = getByIdOrThrow(id);

        order.setEmail(payload.email());
        order.setProductId(payload.productId());
        order.setHasBeenSent(payload.hasBeenSent());

        repository.save(order);

        if (order.getHasBeenSent()) {
            EmailRequest emailRequest = EmailRequest.builder()
                    .topic("Order update")
                    .recipientEmail(order.getEmail())
                    .body("Your order has been sent")
                    .build();

            sendEmailMessage(emailRequest);
        }

        return mapper.map(order);
    }

    private Order getByIdOrThrow(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> {
                    throw new NotFoundException(
                            "Order with provided id not found");
                });
    }

    private void sendEmailMessage(EmailRequest payload) {
        rabbitTemplate.convertAndSend(
                MQConfig.EMAIL_EXCHANGE,
                MQConfig.EMAIL_ROUTING_KEY,
                payload
        );
    }
}

