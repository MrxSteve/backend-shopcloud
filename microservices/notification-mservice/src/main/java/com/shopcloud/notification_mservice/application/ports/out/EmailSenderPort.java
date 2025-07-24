package com.shopcloud.notification_mservice.application.ports.out;

public interface EmailSenderPort {
    void sendEmail(String to, String subject, String body);
}
