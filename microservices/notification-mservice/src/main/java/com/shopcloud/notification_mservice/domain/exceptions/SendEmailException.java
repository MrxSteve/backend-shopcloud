package com.shopcloud.notification_mservice.domain.exceptions;

public class SendEmailException extends RuntimeException {
    public SendEmailException(String message) {
        super(message);
    }
}
