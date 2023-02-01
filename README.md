# Spring RabbitMQ Demo

## Description

Aim of the project to familiarise myself with RabbitMQ. Project consists of two Spring Boot application:  
- `OrderService` - producer
- `EmailService` - consumer
Each time order is created or order's `hasBeenSent` field is updated to true `OrderService` sends message to queue to inform `EmailService` that email with proper message must be sent.
