package com.shopcloud.notification_mservice.infra.adapters.out.sql.adapter;

import com.shopcloud.notification_mservice.application.ports.out.EmailLogPersistencePort;
import com.shopcloud.notification_mservice.domain.models.EmailLogModel;
import com.shopcloud.notification_mservice.infra.adapters.out.sql.entity.EmailLogEntity;
import com.shopcloud.notification_mservice.infra.adapters.out.sql.mapper.EmailLogEntityMapper;
import com.shopcloud.notification_mservice.infra.adapters.out.sql.repository.EmailLogJpaRepository;
import com.shopcloud.notification_mservice.infra.adapters.out.sql.repository.EmailTemplateJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class EmailLogJpaAdapter implements EmailLogPersistencePort {
    private final EmailLogJpaRepository emailLogJpaRepository;
    private final EmailLogEntityMapper emailLogEntityMapper;
    private final EmailTemplateJpaRepository emailTemplateJpaRepository;

    @Override
    public EmailLogModel save(EmailLogModel log) {
        EmailLogEntity entity = emailLogEntityMapper.toEntity(log);

        entity.setTemplate(
                emailTemplateJpaRepository.findByCodeIgnoreCase(log.getTemplateCode())
                        .orElse(null)
        );

        EmailLogEntity savedEntity = emailLogJpaRepository.save(entity);
        return emailLogEntityMapper.toModel(savedEntity);
    }

    @Override
    public List<EmailLogModel> findAll(int page, int size) {
        return emailLogJpaRepository.findAll(PageRequest.of(page, size))
                .stream()
                .map(emailLogEntityMapper::toModel)
                .toList();
    }

    @Override
    public Optional<EmailLogModel> findById(UUID id) {
        return emailLogJpaRepository.findById(id)
                .map(emailLogEntityMapper::toModel);
    }

    @Override
    public List<EmailLogModel> findByStatus(String status, int page, int size) {
        return emailLogJpaRepository.findByStatusIgnoreCase(status, PageRequest.of(page, size))
                .stream()
                .map(emailLogEntityMapper::toModel)
                .toList();
    }

    @Override
    public List<EmailLogModel> findByToEmail(String toEmail, int page, int size) {
        return emailLogJpaRepository.findByToEmailIgnoreCase(toEmail, PageRequest.of(page, size))
                .stream()
                .map(emailLogEntityMapper::toModel)
                .toList();
    }
}
