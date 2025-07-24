package com.shopcloud.notification_mservice.application.usecases;

import com.shopcloud.notification_mservice.application.ports.in.IEmailTemplateUseCases;
import com.shopcloud.notification_mservice.application.ports.out.EmailTemplatePersistencePort;
import com.shopcloud.notification_mservice.domain.exceptions.ResourceAlreadyExistsException;
import com.shopcloud.notification_mservice.domain.exceptions.ResourceNotFoundException;
import com.shopcloud.notification_mservice.domain.models.EmailTemplateModel;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class EmailTemplateService implements IEmailTemplateUseCases {
    private final EmailTemplatePersistencePort emailTemplatePersistencePort;

    @Override
    public EmailTemplateModel createTemplate(EmailTemplateModel model) {
        if (emailTemplatePersistencePort.existsByCode(model.getCode())) {
            throw new ResourceAlreadyExistsException("Template with code " + model.getCode() + " already exists.");
        }
        return emailTemplatePersistencePort.save(model);
    }

    @Override
    public EmailTemplateModel updateTemplate(UUID id, EmailTemplateModel model) {
        EmailTemplateModel existing = emailTemplatePersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found with ID: " + id));

        if (model.getCode() != null) existing.setCode(model.getCode());
        if (model.getDescription() != null) existing.setDescription(model.getDescription());
        if (model.getSubjectTemplate() != null) existing.setSubjectTemplate(model.getSubjectTemplate());
        if (model.getBodyTemplate() != null) existing.setBodyTemplate(model.getBodyTemplate());

        return emailTemplatePersistencePort.save(existing);
    }

    @Override
    public void deleteTemplate(UUID id) {
        this.getTemplateById(id);
        emailTemplatePersistencePort.deleteById(id);
    }

    @Override
    public EmailTemplateModel getTemplateById(UUID id) {
        return emailTemplatePersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found with ID: " + id));
    }

    @Override
    public EmailTemplateModel getTemplateByCode(String code) {
        return emailTemplatePersistencePort.findByCode(code)
                .orElseThrow(() -> new ResourceNotFoundException("Template not found with code: " + code));
    }

    @Override
    public List<EmailTemplateModel> getAllTemplates() {
        return emailTemplatePersistencePort.findAll();
    }
}
