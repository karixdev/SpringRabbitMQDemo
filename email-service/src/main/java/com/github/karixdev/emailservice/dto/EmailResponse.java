package com.github.karixdev.emailservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmailResponse(
        @JsonProperty("topic")
        String topic,
        @JsonProperty("recipient_email")
        String recipientEmail,
        @JsonProperty("body")
        String body
) {}
