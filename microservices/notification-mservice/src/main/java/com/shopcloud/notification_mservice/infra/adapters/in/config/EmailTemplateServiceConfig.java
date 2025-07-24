package com.shopcloud.notification_mservice.infra.adapters.in.config;

import com.shopcloud.notification_mservice.application.ports.in.IEmailTemplateUseCases;
import com.shopcloud.notification_mservice.application.ports.out.EmailTemplatePersistencePort;
import com.shopcloud.notification_mservice.application.usecases.EmailTemplateService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmailTemplateServiceConfig {
    @Bean
    public EmailTemplateService emailTemplateService(EmailTemplatePersistencePort emailTemplatePersistencePort) {
        return new EmailTemplateService(emailTemplatePersistencePort);
    }

    @Bean
    IEmailTemplateUseCases iEmailTemplateUseCases(EmailTemplateService emailTemplateService) {
        return emailTemplateService;
    }
}
