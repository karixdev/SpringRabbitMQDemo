package com.github.karixdev.orderservice.mapper;

import com.github.karixdev.orderservice.dto.OrderResponse;
import com.github.karixdev.orderservice.entity.Order;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class OrderDtoMapper implements DtoMapper<Order, OrderResponse> {
    @Override
    public OrderResponse map(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getEmail(),
                order.getProductId(),
                order.getHasBeenSent()
        );
    }

    @Override
    public List<OrderResponse> map(Collection<Order> orders) {
        return orders.stream()
                .map(this::map)
                .toList();
    }
}
