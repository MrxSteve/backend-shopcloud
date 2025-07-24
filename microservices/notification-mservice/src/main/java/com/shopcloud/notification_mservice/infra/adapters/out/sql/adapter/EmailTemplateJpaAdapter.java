package com.shopcloud.notification_mservice.infra.adapters.out.sql.adapter;

import com.shopcloud.notification_mservice.application.ports.out.EmailTemplatePersistencePort;
import com.shopcloud.notification_mservice.domain.models.EmailTemplateModel;
import com.shopcloud.notification_mservice.infra.adapters.out.sql.entity.EmailTemplateEntity;
import com.shopcloud.notification_mservice.infra.adapters.out.sql.mapper.EmailTemplateEntityMapper;
import com.shopcloud.notification_mservice.infra.adapters.out.sql.repository.EmailTemplateJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EmailTemplateJpaAdapter implements EmailTemplatePersistencePort {
    private final EmailTemplateJpaRepository emailTemplateJpaRepository;
    private final EmailTemplateEntityMapper emailTemplateEntityMapper;

    @Override
    public EmailTemplateModel save(EmailTemplateModel model) {
        EmailTemplateEntity entity = emailTemplateEntityMapper.toEntity(model);
        EmailTemplateEntity savedEntity = emailTemplateJpaRepository.save(entity);
        return emailTemplateEntityMapper.toModel(savedEntity);
    }

    @Override
    public void deleteById(UUID id) {
        emailTemplateJpaRepository.deleteById(id);
    }

    @Override
    public Optional<EmailTemplateModel> findById(UUID id) {
        return emailTemplateJpaRepository.findById(id)
                .map(emailTemplateEntityMapper::toModel);
    }

    @Override
    public Optional<EmailTemplateModel> findByCode(String code) {
        return emailTemplateJpaRepository.findByCodeIgnoreCase(code)
                .map(emailTemplateEntityMapper::toModel);
    }

    @Override
    public List<EmailTemplateModel> findAll() {
        return emailTemplateJpaRepository.findAll()
                .stream().
                map(emailTemplateEntityMapper::toModel)
                .collect(Collectors.toList());
    }

    @Override
    public Boolean existsByCode(String code) {
        return emailTemplateJpaRepository.existsByCode(code);
    }
}
