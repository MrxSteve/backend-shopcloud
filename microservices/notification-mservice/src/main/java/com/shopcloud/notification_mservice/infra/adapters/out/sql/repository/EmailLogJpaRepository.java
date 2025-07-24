package com.shopcloud.notification_mservice.infra.adapters.out.sql.repository;

import com.shopcloud.notification_mservice.infra.adapters.out.sql.entity.EmailLogEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmailLogJpaRepository extends JpaRepository<EmailLogEntity, UUID> {
    Page<EmailLogEntity> findAll(Pageable pageable);
    Page<EmailLogEntity> findByToEmailIgnoreCase(String toEmail, Pageable pageable);
    Page<EmailLogEntity> findByStatusIgnoreCase(String status, Pageable pageable);
}
