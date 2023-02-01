package com.github.karixdev.emailservice.service;

import com.github.karixdev.emailservice.exception.EmailSendingException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {
    private final JavaMailSender mailSender;

    public void send(
            String sender,
            String recipient,
            String topic,
            String body
    ) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(mimeMessage, "utf-8");

        try {
            helper.setFrom(sender);
            helper.setTo(recipient);

            helper.setSubject(topic);
            helper.setText(body);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Error while sending an email: ", e);
            throw new EmailSendingException();
        }
    }
}
