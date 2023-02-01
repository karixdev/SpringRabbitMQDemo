package com.github.karixdev.orderservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record EmailRequest(
        @JsonProperty("topic")
        String topic,
        @JsonProperty("recipient_email")
        String recipientEmail,
        @JsonProperty("body")
        String body
) {}
