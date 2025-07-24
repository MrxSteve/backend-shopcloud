package com.shopcloud.notification_mservice.infra.adapters.in.config;

import com.shopcloud.notification_mservice.application.ports.in.IEmailLogUseCases;
import com.shopcloud.notification_mservice.application.ports.out.EmailLogPersistencePort;
import com.shopcloud.notification_mservice.application.usecases.EmailLogService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailLogServiceConfig {
    @Bean
    public EmailLogService emailLogService(EmailLogPersistencePort emailLogPersistencePort) {
        return new EmailLogService(emailLogPersistencePort);
    }

    @Bean
    public IEmailLogUseCases iEmailLogUseCases(EmailLogService emailLogService) {
        return emailLogService;
    }
}
