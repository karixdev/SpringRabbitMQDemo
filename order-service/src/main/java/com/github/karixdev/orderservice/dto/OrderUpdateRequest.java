package com.github.karixdev.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderUpdateRequest(
        @JsonProperty("email")
        String email,
        @JsonProperty("product_id")
        Long productId,
        @JsonProperty("has_been_sent")
        Boolean hasBeenSent
) {}
