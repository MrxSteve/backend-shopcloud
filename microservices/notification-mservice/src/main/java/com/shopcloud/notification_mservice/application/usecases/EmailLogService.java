package com.shopcloud.notification_mservice.application.usecases;

import com.shopcloud.notification_mservice.application.ports.in.IEmailLogUseCases;
import com.shopcloud.notification_mservice.application.ports.out.EmailLogPersistencePort;
import com.shopcloud.notification_mservice.domain.exceptions.ResourceNotFoundException;
import com.shopcloud.notification_mservice.domain.models.EmailLogModel;
import lombok.RequiredArgsConstructor;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
public class EmailLogService implements IEmailLogUseCases {
    private final EmailLogPersistencePort emailLogPersistencePort;

    @Override
    public EmailLogModel createLog(EmailLogModel log) {
        log.setCreatedAt(OffsetDateTime.now());
        return emailLogPersistencePort.save(log);
    }

    @Override
    public List<EmailLogModel> getAllLogs(int page, int size) {
        return emailLogPersistencePort.findAll(page, size);
    }

    @Override
    public EmailLogModel getLogById(UUID id) {
        return emailLogPersistencePort.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Log not found with ID: " + id));
    }

    @Override
    public List<EmailLogModel> getLogsByStatus(String status, int page, int size) {
        return emailLogPersistencePort.findByStatus(status, page, size);
    }

    @Override
    public List<EmailLogModel> getLogsByEmail(String toEmail, int page, int size) {
        return emailLogPersistencePort.findByToEmail(toEmail, page, size);
    }
}

