package com.github.karixdev.emailservice.listener;

import com.github.karixdev.emailservice.config.MQConfig;
import com.github.karixdev.emailservice.dto.EmailResponse;
import com.github.karixdev.emailservice.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageListener {
    private final EmailService emailService;

    @RabbitListener(queues = MQConfig.EMAIL_QUEUE)
    private void listen(EmailResponse message) {
        emailService.send(
                "email-service@company.com",
                message.recipientEmail(),
                message.topic(),
                message.body()
        );
    }
}
