package com.shopcloud.notification_mservice.application.ports.out;

import com.shopcloud.notification_mservice.domain.models.EmailTemplateModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmailTemplatePersistencePort {
    EmailTemplateModel save(EmailTemplateModel model);
    void deleteById(UUID id);
    Optional<EmailTemplateModel> findById(UUID id);
    Optional<EmailTemplateModel> findByCode(String code);
    List<EmailTemplateModel> findAll();
    Boolean existsByCode(String code);
}
