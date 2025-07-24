package com.shopcloud.notification_mservice.application.ports.in;

import com.shopcloud.notification_mservice.domain.models.EmailMessageModel;

public interface EmailMessageListener {
    void receiveEmailMessage(EmailMessageModel message);
}

