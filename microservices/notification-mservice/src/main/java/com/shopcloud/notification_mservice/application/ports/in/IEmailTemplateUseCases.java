package com.shopcloud.notification_mservice.application.ports.in;

import com.shopcloud.notification_mservice.domain.models.EmailTemplateModel;

import java.util.List;
import java.util.UUID;

public interface IEmailTemplateUseCases {
    EmailTemplateModel createTemplate(EmailTemplateModel model);
    EmailTemplateModel updateTemplate(UUID id, EmailTemplateModel model);
    void deleteTemplate(UUID id);
    EmailTemplateModel getTemplateById(UUID id);
    EmailTemplateModel getTemplateByCode(String code);
    List<EmailTemplateModel> getAllTemplates();
}
