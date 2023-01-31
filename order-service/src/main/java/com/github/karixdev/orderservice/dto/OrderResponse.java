package com.github.karixdev.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public record OrderResponse(
        @JsonProperty("id")
        UUID id,
        @JsonProperty("email")
        String email,
        @JsonProperty("product_id")
        Long productId,
        @JsonProperty("has_been_sent")
        Boolean hasBeenSent
) {}
