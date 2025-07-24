package com.shopcloud.notification_mservice.application.ports.out;

import com.shopcloud.notification_mservice.domain.models.EmailLogModel;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface EmailLogPersistencePort {
    EmailLogModel save(EmailLogModel log);
    List<EmailLogModel> findAll(int page, int size);
    Optional<EmailLogModel> findById(UUID id);
    List<EmailLogModel> findByStatus(String status, int page, int size);
    List<EmailLogModel> findByToEmail(String toEmail, int page, int size);
}
