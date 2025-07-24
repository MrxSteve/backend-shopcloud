package com.shopcloud.notification_mservice.infra.adapters.in.config;

import com.shopcloud.notification_mservice.application.ports.in.EmailMessageListener;
import com.shopcloud.notification_mservice.application.ports.out.EmailLogPersistencePort;
import com.shopcloud.notification_mservice.application.ports.out.EmailSenderPort;
import com.shopcloud.notification_mservice.application.ports.out.EmailTemplatePersistencePort;
import com.shopcloud.notification_mservice.application.usecases.EmailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailServiceConfig {
    @Bean
    public EmailService emailService(EmailSenderPort emailSenderPort,
                                     EmailLogPersistencePort emailLogPersistencePort,
                                     EmailTemplatePersistencePort emailTemplatePersistencePort) {
        return new EmailService(emailSenderPort, emailLogPersistencePort, emailTemplatePersistencePort);
    }

    @Bean
    public EmailMessageListener emailMessageListener(EmailService emailService) {
        return emailService;
    }
}
