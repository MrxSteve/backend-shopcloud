package com.shopcloud.notification_mservice.application.ports.in;

import com.shopcloud.notification_mservice.domain.models.EmailLogModel;

import java.util.List;
import java.util.UUID;

public interface IEmailLogUseCases {
    EmailLogModel createLog(EmailLogModel log);
    List<EmailLogModel> getAllLogs(int page, int size);
    EmailLogModel getLogById(UUID id);
    List<EmailLogModel> getLogsByStatus(String status, int page, int size);
    List<EmailLogModel> getLogsByEmail(String toEmail, int page, int size);
}
