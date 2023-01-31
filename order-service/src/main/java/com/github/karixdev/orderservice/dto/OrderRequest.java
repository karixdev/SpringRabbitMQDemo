package com.github.karixdev.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OrderRequest(
        @JsonProperty("email")
        String email,
        @JsonProperty("product_id")
        Long productId
) {}
