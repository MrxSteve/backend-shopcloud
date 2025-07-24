package com.shopcloud.notification_mservice.infra.adapters.out.sql.repository;

import com.shopcloud.notification_mservice.infra.adapters.out.sql.entity.EmailTemplateEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EmailTemplateJpaRepository extends JpaRepository<EmailTemplateEntity, UUID> {
    Optional<EmailTemplateEntity> findByCodeIgnoreCase(String code);
    Boolean existsByCode(String code);
}
