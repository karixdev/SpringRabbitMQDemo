package com.github.karixdev.emailservice.listener;

import com.github.karixdev.emailservice.config.MQConfig;
import com.github.karixdev.emailservice.dto.EmailResponse;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageListener {
    @RabbitListener(queues = MQConfig.EMAIL_QUEUE)
    private void listen(EmailResponse message) {
        System.out.println(message);
    }
}
