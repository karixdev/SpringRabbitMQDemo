package com.github.karixdev.emailservice.exception;

public class EmailSendingException extends RuntimeException {
    public EmailSendingException() {
        super("Error occurred while sending email");
    }
}
